package io.jhoffmann.formulari.check;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jhoffmann.formulari.exception.NotFoundException;
import io.jhoffmann.formulari.template.SingleTemplateResponseDto;
import io.jhoffmann.formulari.template.TemplateEntity;
import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("check")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class CheckController {
    private final CheckService service;

    public CheckController(CheckService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void addCheck(@RequestBody CreateCheckRequestDto dto, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        CheckEntity savedCheck = service.createCheck(dto.getName(), dto.getTransmissionType(), dto.getTemplateName(),
                dto.getRecipients(), userDetails);

        List<CheckRecipientEntity> checkRecipients = service.createCheckRecipients(savedCheck, dto.getRecipients());

        service.informCheckRecipients(checkRecipients);
    }

    @PostMapping("reply")
    @PermitAll
    public void answerCheck(@RequestBody CheckReplyRequestDto dto) {
        service.answerCheck(dto.getUid(), dto.getData());
    }

    @GetMapping("template/{uid}")
    public ResponseEntity<SingleTemplateResponseDto> getCheckFields(@PathVariable String uid) {

        TemplateEntity template = service.getTemplateForCheckRecipientUid(uid);

        SingleTemplateResponseDto responseDto = new SingleTemplateResponseDto();

        responseDto.setTemplateName(template.getName());
        responseDto.setComponents(template.getComponents());

        return ResponseEntity.ok(responseDto);

    }

    @GetMapping("inbox")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CheckRecipientsResponseDto> getCheckInbox(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<CheckRecipientEntity> checkRecipients = service.getCheckReplies(userDetails);

        CheckRecipientsResponseDto response = new CheckRecipientsResponseDto();

        response.setRecipients(checkRecipients);

        return ResponseEntity.ok(response);
    }

    @GetMapping("outbox")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CheckRecipientsResponseDto> getCheckOutbox(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<CheckRecipientEntity> checkRecipients = service.getOpenCheckReplies(userDetails);

        CheckRecipientsResponseDto response = new CheckRecipientsResponseDto();

        response.setRecipients(checkRecipients);

        return ResponseEntity.ok(response);
    }

    @GetMapping("archive")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CheckRecipientsResponseDto> getCheckArchived(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<CheckRecipientEntity> checkRecipients = service.getArchivedCheckReplies(userDetails);

        CheckRecipientsResponseDto response = new CheckRecipientsResponseDto();

        response.setRecipients(checkRecipients);

        return ResponseEntity.ok(response);
    }

    @PostMapping("remind/{uid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void remind(@PathVariable String uid) {
       
        Optional<CheckRecipientEntity> optCheckRecipient = service.findCheckRecipientByUid(uid);

        if(optCheckRecipient.isEmpty()){
            throw new NotFoundException();
        }

        CheckRecipientEntity checkRecipient = optCheckRecipient.get();

        this.service.sendCheckReminder(checkRecipient);
    }
}

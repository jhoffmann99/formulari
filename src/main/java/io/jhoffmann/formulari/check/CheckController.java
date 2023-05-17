package io.jhoffmann.formulari.check;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jhoffmann.formulari.template.SingleTemplateResponseDto;
import io.jhoffmann.formulari.template.TemplateEntity;

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
    public void addCheck(@RequestBody CreateCheckRequestDto dto) {
        CheckEntity savedCheck = service.createCheck(dto.getName(), dto.getTransmissionType(), dto.getTemplateName(),
                dto.getRecipients());

        List<CheckRecipientEntity> checkRecipients = service.createCheckRecipients(savedCheck, dto.getRecipients());

        service.informCheckRecipients(checkRecipients);
    }

    @PostMapping("reply")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void replyCheck(@RequestBody CheckReplyRequestDto dto) {
        service.replyCheck(dto.getUid(), dto.getData());
    }

    @GetMapping("template/{uid}")
    public ResponseEntity<SingleTemplateResponseDto> getCheckFields(@PathVariable String uid) {

        TemplateEntity template = service.getTemplateForCheckRecipientUid(uid);

        SingleTemplateResponseDto responseDto = new SingleTemplateResponseDto();

        responseDto.setTemplateName(template.getName());
        responseDto.setComponents(template.getComponents());

        return ResponseEntity.ok(responseDto);

    }
}

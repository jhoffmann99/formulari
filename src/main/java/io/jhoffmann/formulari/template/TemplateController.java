package io.jhoffmann.formulari.template;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/template")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SingleTemplateResponseDto> createTemplate(@Valid @RequestBody CreateTemplateRequestDto dto,
            Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        TemplateEntity template = templateService.createTemplate(dto.getName(),
                dto.getComponents().getComponents(), userDetails);

        SingleTemplateResponseDto responseDto = TemplateMapper.singleTemplateEntityToDto(template);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("{templateName}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SingleTemplateResponseDto> getSingleTemplate(@PathVariable String templateName) {
        Optional<TemplateEntity> optTemplate = templateService.findTemplateByUid(templateName);

        if (optTemplate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SingleTemplateResponseDto responseDto = TemplateMapper.singleTemplateEntityToDto(optTemplate.get());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AllTemplatesResponseDto> getAllTemplatesByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        AllTemplatesResponseDto responseDto = new AllTemplatesResponseDto();

        List<TemplateEntity> templates = templateService.findAllByUser(userDetails);

        List<SingleTemplateResponseDto> templatesResponse = templates.stream().map(template -> {

            return TemplateMapper.singleTemplateEntityToDto(template);

        }).toList();

        responseDto.setTemplates(templatesResponse);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("{uid}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public void deleteTemplateByUid(@PathVariable String uid, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        try {
            templateService.deleteByUid(uid, userDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

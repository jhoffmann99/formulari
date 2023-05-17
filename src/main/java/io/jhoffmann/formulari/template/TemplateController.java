package io.jhoffmann.formulari.template;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("template")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SingleTemplateResponseDto> createTemplate(@Valid @RequestBody CreateTemplateRequestDto dto) {

        TemplateEntity template = templateService.createTemplate(dto.getTemplateName(),
                dto.getComponents().getComponents());

        SingleTemplateResponseDto responseDto = TemplateMapper.singleTemplateEntityToDto(template);

        return ResponseEntity.ok(responseDto);
    }
    
    @GetMapping("{templateName}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SingleTemplateResponseDto> getSingleTemplate(@PathVariable String templateName) {
        Optional<TemplateEntity> optTemplate = templateService.findTemplateByName(templateName);

        if (optTemplate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SingleTemplateResponseDto responseDto = TemplateMapper.singleTemplateEntityToDto(optTemplate.get());

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AllTemplatesResponseDto> getAllTemplates() {
        AllTemplatesResponseDto responseDto = new AllTemplatesResponseDto();
        responseDto.setTemplates(templateService.findAll());

        return ResponseEntity.ok(responseDto);
    }


}

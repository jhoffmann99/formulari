package io.jhoffmann.formulari.template;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template")
public class CreateTemplateController {
    private final TemplateService templateService;

    public CreateTemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<TemplateEntity> createTemplate(@RequestBody CreateTemplateRequestDto dto) {

        TemplateEntity template = templateService.createTemplate(dto.getTemplateName(), dto.getComponents());

        return ResponseEntity.ok(template);
    }


}

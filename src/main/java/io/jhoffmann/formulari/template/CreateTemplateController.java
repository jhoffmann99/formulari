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
    public ResponseEntity<SingleTemplateResponseDto> createTemplate(@RequestBody CreateTemplateRequestDto dto) {

        TemplateEntity template = templateService.createTemplate(dto.getTemplateName(),
                dto.getComponents().getComponents());
        
        SingleTemplateResponseDto responseDto = new SingleTemplateResponseDto();
        responseDto.setComponents(template.getComponents());
        responseDto.setTemplateName(dto.getTemplateName());

        return ResponseEntity.ok(responseDto);
    }


}

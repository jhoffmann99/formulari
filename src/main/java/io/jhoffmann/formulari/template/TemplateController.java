package io.jhoffmann.formulari.template;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("template")
public class TemplateController {
    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<SingleTemplateResponseDto> createTemplate(@RequestBody CreateTemplateRequestDto dto) {

        TemplateEntity template = templateService.createTemplate(dto.getTemplateName(),
                dto.getComponents().getComponents());

        SingleTemplateResponseDto responseDto = TemplateMapper.singleTemplateEntityToDto(template);

        return ResponseEntity.ok(responseDto);
    }
    
    @GetMapping("{templateName}")
    public ResponseEntity<SingleTemplateResponseDto> getSingleTemplate(@PathVariable String templateName) {
        Optional<TemplateEntity> optTemplate = templateService.findTemplateByName(templateName);

        if (optTemplate.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SingleTemplateResponseDto responseDto = TemplateMapper.singleTemplateEntityToDto(optTemplate.get());

        return ResponseEntity.ok(responseDto);
    }


}

package io.jhoffmann.formulari.template;

import java.util.List;

import org.springframework.stereotype.Service;

import io.jhoffmann.formulari.model.AbstractComponent;

@Service
public class TemplateService {
    private final TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public TemplateEntity createTemplate(String templateName, Components components) {

        TemplateEntity template = new TemplateEntity();
        template.setName(templateName);
        template.setComponents(components);

        return repository.save(template);
    }

    
}

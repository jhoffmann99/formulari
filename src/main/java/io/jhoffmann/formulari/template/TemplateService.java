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

    public TemplateEntity createTemplate(String templateName, List<? extends AbstractComponent> components) {

        TemplateEntity template = new TemplateEntity();
        template.setName(templateName);

        ComponentWrapper components2 = new ComponentWrapper();
        components2.setComponents(components);

        template.setComponents(components2);

        return repository.save(template);
    }

}

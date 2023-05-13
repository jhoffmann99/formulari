package io.jhoffmann.formulari.template;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class SingleTemplateResponseDto {
    private String templateName;

    @JsonUnwrapped
    private ComponentWrapper components;
    
   
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String name) {
        this.templateName = name;
    }

    public ComponentWrapper getComponents() {
        return components;
    }

    public void setComponents(ComponentWrapper components) {
        this.components = components;
    }

}

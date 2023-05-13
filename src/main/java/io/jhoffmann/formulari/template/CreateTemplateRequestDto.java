package io.jhoffmann.formulari.template;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class CreateTemplateRequestDto {
    @NotBlank
    private String templateName;

    @Valid
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

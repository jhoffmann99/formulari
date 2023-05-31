package io.jhoffmann.formulari.template;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class CreateTemplateRequestDto {
    @NotBlank
    private String name;

    @Valid
    @JsonUnwrapped
    private ComponentWrapper components;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentWrapper getComponents() {
        return components;
    }

    public void setComponents(ComponentWrapper components) {
        this.components = components;
    }

}

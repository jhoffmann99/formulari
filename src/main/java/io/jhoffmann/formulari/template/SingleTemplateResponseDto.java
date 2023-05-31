package io.jhoffmann.formulari.template;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class SingleTemplateResponseDto {
    private String name;
    private String uid;
    private String createdAt;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}

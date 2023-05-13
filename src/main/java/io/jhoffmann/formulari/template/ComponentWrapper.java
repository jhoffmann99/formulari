package io.jhoffmann.formulari.template;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.jhoffmann.formulari.model.AbstractComponent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComponentWrapper implements Serializable {
    private List<? extends AbstractComponent> components;

    public List<? extends AbstractComponent> getComponents() {
        return components;
    }

    public void setComponents(List<? extends AbstractComponent> components) {
        this.components = components;
    }

}

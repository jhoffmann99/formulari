package io.jhoffmann.formulari.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.validation.constraints.NotBlank;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = TextField.class, name = "TEXT"),
        @Type(value = NumberField.class, name = "NUMBER"),
        @Type(value = DateField.class, name = "DATE")
})
public abstract class AbstractComponent {
    @NotBlank
    private String name;
    private boolean required;
    @NotBlank
    private String description;
    @NotBlank
    private String hint;

    public AbstractComponent() {
    }
    
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   
    public boolean isRequired() {
        return required;
    }
    public void setRequired(boolean required) {
        this.required = required;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getHint() {
        return hint;
    }
    public void setHint(String hint) {
        this.hint = hint;
    }
    
}

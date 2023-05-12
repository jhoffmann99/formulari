package io.jhoffmann.formulari.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = TextField.class, name = "text"),
        @Type(value = NumberField.class, name = "number"),
        @Type(value = DateField.class, name = "date")
})
public abstract class AbstractComponent {
    /* private String name;
    private boolean required;
    private String description;
    private String hint; */

    public AbstractComponent() {}
    
    /* public String getName() {
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
    } */

    
}

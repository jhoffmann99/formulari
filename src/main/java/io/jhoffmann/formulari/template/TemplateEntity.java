package io.jhoffmann.formulari.template;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import io.jhoffmann.formulari.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "template")
public class TemplateEntity extends AbstractEntity{

    private String name;

    @Column(name = "components", nullable = false, columnDefinition = "TEXT")
    @Type(JsonType.class)
    private Components components;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }



    
    
}

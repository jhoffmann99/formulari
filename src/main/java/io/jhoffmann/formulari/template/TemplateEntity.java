package io.jhoffmann.formulari.template;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import io.jhoffmann.formulari.AbstractEntity;
import io.jhoffmann.formulari.auth.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "jh_template")
public class TemplateEntity extends AbstractEntity {

    private String name;

    private String uid;

    @Enumerated(EnumType.STRING)
    private TemplateStatus status;

    @Column(name = "components", nullable = false, columnDefinition = "TEXT")
    @Type(JsonType.class)
    private ComponentWrapper components;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String createdAt;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @PrePersist
    private void prePersist() {
        this.uid = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now().toString();
        this.status = TemplateStatus.ACTIVE;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public TemplateStatus getStatus() {
        return status;
    }

    public void setStatus(TemplateStatus status) {
        this.status = status;
    }

}

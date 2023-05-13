package io.jhoffmann.formulari.check;

import java.time.LocalDateTime;
import java.util.List;

import io.jhoffmann.formulari.AbstractEntity;
import io.jhoffmann.formulari.template.TemplateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "jh_check")
public class CheckEntity extends AbstractEntity{
    private String name;

    @ManyToOne
    @JoinColumn(name="template_id", nullable=false)
    private TemplateEntity template;

    private List<String> recipients;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateEntity getTemplate() {
        return template;
    }

    public void setTemplate(TemplateEntity template) {
        this.template = template;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }
    

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    // Status
    // DRAFT, REQUESTED, ARCHIVED, TRASH

    
}

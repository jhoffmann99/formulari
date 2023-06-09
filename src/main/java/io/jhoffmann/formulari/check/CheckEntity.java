package io.jhoffmann.formulari.check;

import java.time.LocalDateTime;

import io.jhoffmann.formulari.AbstractEntity;
import io.jhoffmann.formulari.auth.User;
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
public class CheckEntity extends AbstractEntity {
    private String name;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private TemplateEntity template;

    private String subject;

    private String greeting;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    private int expectedReplies;

    private int totalReplies;

    @Enumerated(EnumType.STRING)
    private CheckStatus status;

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

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public int getExpectedReplies() {
        return expectedReplies;
    }

    public void setExpectedReplies(int expectedReplies) {
        this.expectedReplies = expectedReplies;
    }

    public int getTotalReplies() {
        return totalReplies;
    }

    public boolean isCompleted() {
        return expectedReplies == totalReplies;
    }

    public void setTotalReplies(int totalReplies) {
        this.totalReplies = totalReplies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = CheckStatus.REQUESTED;
    }

    @PreUpdate
    private void preUpdate() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    public void increaseTotalReplies() {
        totalReplies += 1;
        if (totalReplies == expectedReplies) {
            this.status = CheckStatus.ARCHIVED;
        }
    }

}

package io.jhoffmann.formulari.subscription;

import java.time.LocalDateTime;



import io.jhoffmann.formulari.AbstractEntity;
import io.jhoffmann.formulari.auth.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jh_subscription")
public class SubscriptionEntity extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
    private LocalDateTime activatedAt;
    private LocalDateTime canceledAt;

    @ManyToOne
    private SubscriptionPlanEntity plan;

    @ManyToOne
    private User user;
    
    public SubscriptionStatus getStatus() {
        return status;
    }
    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }
    public LocalDateTime getActivatedAt() {
        return activatedAt;
    }
    public void setActivatedAt(LocalDateTime activatedAt) {
        this.activatedAt = activatedAt;
    }
    public LocalDateTime getCanceledAt() {
        return canceledAt;
    }
    public void setCanceledAt(LocalDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public SubscriptionPlanEntity getPlan() {
        return plan;
    }
    public void setPlan(SubscriptionPlanEntity plan) {
        this.plan = plan;
    }

    

}

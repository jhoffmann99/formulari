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
    private String paypalSubscriptionid;
    private String activatedAt;
    private String canceledAt;

    @ManyToOne
    private User user;
    
    public SubscriptionStatus getStatus() {
        return status;
    }
    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }
    
    public String getActivatedAt() {
        return activatedAt;
    }
    public void setActivatedAt(String activatedAt) {
        this.activatedAt = activatedAt;
    }
    public String getCanceledAt() {
        return canceledAt;
    }
    public void setCanceledAt(String canceledAt) {
        this.canceledAt = canceledAt;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getPaypalSubscriptionid() {
        return paypalSubscriptionid;
    }
    public void setPaypalSubscriptionid(String paypalSubscriptionid) {
        this.paypalSubscriptionid = paypalSubscriptionid;
    }

    

}

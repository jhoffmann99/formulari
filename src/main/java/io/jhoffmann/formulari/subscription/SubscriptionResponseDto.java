package io.jhoffmann.formulari.subscription;

import java.time.LocalDateTime;

public class SubscriptionResponseDto {

    private SubscriptionPlanEntity plan;
    private SubscriptionStatus status;
    private String activatedAt;
   
    public SubscriptionPlanEntity getPlan() {
        return plan;
    }
    public void setPlan(SubscriptionPlanEntity plan) {
        this.plan = plan;
    }
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

    
    
    
}

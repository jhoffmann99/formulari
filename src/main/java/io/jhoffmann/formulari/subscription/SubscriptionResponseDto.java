package io.jhoffmann.formulari.subscription;

public class SubscriptionResponseDto {

    private SubscriptionStatus status;
    private String activatedAt;
 
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

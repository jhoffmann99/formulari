package io.jhoffmann.formulari.subscription;

import io.jhoffmann.formulari.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "jh_subscription_plan")
public class SubscriptionPlanEntity extends AbstractEntity {
    private String name;
    private int emailsPerMonth;
    private boolean statistics;
    private boolean emailNotifications;
    @Enumerated(EnumType.STRING)
    private SubscriptionPeriod period;
    private int priceInEuroCents;
    @Enumerated(EnumType.STRING)
    private SubscriptionPlanStatus status;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getEmailsPerMonth() {
        return emailsPerMonth;
    }
    public void setEmailsPerMonth(int emailsPerMonth) {
        this.emailsPerMonth = emailsPerMonth;
    }
    public boolean hasStatistics() {
        return statistics;
    }
    public void setStatistics(boolean statistics) {
        this.statistics = statistics;
    }
    public boolean hasEmailNotifications() {
        return emailNotifications;
    }
    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }
    public SubscriptionPeriod getPeriod() {
        return period;
    }
    public void setPeriod(SubscriptionPeriod period) {
        this.period = period;
    }
    public int getPriceInEuroCents() {
        return priceInEuroCents;
    }

    public void setPriceInEuroCents(int priceInEuroCents) {
        this.priceInEuroCents = priceInEuroCents;
    }
    
    public SubscriptionPlanStatus getStatus() {
        return status;
    }
    public void setStatus(SubscriptionPlanStatus status) {
        this.status = status;
    }

    
}

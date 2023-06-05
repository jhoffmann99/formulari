package io.jhoffmann.formulari.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionRequestDto {
    private SubscriptionStatus status;
    private String id;
    @JsonProperty("start_time")
    private String startTime;

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "SubscriptionRequestDto [status=" + status + ", id=" + id + ", startTime=" + startTime + "]";
    }

}

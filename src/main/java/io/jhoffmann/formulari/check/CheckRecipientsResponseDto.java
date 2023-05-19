package io.jhoffmann.formulari.check;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

public class CheckRecipientsResponseDto {
    @JsonValue
    private List<CheckRecipientEntity> recipients;

    public List<CheckRecipientEntity> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<CheckRecipientEntity> recipients) {
        this.recipients = recipients;
    }

    
}

package io.jhoffmann.formulari.check;

import java.util.List;

public class CreateCheckRequestDto {
    private String name;
    private String templateName;
    private TransmissionType transmissionType;
    private List<CheckRecipientDto> recipients;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTemplateName() {
        return templateName;
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    public TransmissionType getTransmissionType() {
        return transmissionType;
    }
    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }
    public List<CheckRecipientDto> getRecipients() {
        return recipients;
    }
    public void setRecipients(List<CheckRecipientDto> recipients) {
        this.recipients = recipients;
    }

    
}

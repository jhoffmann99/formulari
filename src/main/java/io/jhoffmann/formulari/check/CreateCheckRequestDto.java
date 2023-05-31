package io.jhoffmann.formulari.check;

import java.util.List;

public class CreateCheckRequestDto {
    private String name;
    private String subject;
    private String greeting;
    private String templateUid;
    private TransmissionType transmissionType;
    private List<CheckRecipientDto> recipients;
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getTemplateUid() {
        return templateUid;
    }
    public void setTemplateUid(String templateUid) {
        this.templateUid = templateUid;
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

    
}

package io.jhoffmann.formulari.email;

import java.util.List;
import java.util.Map;

public class Email {
    private List<String> recipients;
    private String subject;
    private Long templateId;
    private Map<String, String> templateParams;
    private String textPlain;
    
    public List<String> getRecipients() {
        return recipients;
    }
    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Long getTemplateId() {
        return templateId;
    }
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
    public Map<String, String> getTemplateParams() {
        return templateParams;
    }
    public void setTemplateParams(Map<String, String> templateParams) {
        this.templateParams = templateParams;
    }
    public String getTextPlain() {
        return textPlain;
    }
    public void setTextPlain(String textPlain) {
        this.textPlain = textPlain;
    }

    
}

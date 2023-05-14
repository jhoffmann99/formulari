package io.jhoffmann.formulari.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailConfiguration {
    private String apiKey;
    private String senderEmail;
    private String senderName;

    @Bean
    @Scope("singleton")
    public EmailProvider getEmailProvider() {
        EmailProvider provider = new SendinblueEmailProvider(this);

        provider.setApiKey(apiKey);

        return provider;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    
}

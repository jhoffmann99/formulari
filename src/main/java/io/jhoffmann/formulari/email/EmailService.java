package io.jhoffmann.formulari.email;

import org.springframework.stereotype.Service;

import sendinblue.ApiException;

@Service
public class EmailService {
    private final EmailProvider emailProvider;

    public EmailService(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }


    public void sendEmail(Email email) {
        try {
            this.emailProvider.sendEmail(email);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}

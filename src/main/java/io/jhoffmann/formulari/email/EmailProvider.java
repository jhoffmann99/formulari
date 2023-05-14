package io.jhoffmann.formulari.email;

import sendinblue.ApiException;

public interface EmailProvider {
    void sendEmail(Email email) throws ApiException;

    void setApiKey(String apiKey);
}

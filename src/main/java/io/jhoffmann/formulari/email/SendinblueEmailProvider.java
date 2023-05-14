package io.jhoffmann.formulari.email;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

public class SendinblueEmailProvider implements EmailProvider {
    private Logger logger = LoggerFactory.getLogger(SendinblueEmailProvider.class);
    private final EmailConfiguration configuration;
    private TransactionalEmailsApi emailsApi;

    public SendinblueEmailProvider(EmailConfiguration emailConfiguration) {
        this.configuration = emailConfiguration;
    }

    @Override
    public void sendEmail(Email email) throws ApiException {
        final SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        final SendSmtpEmailSender sender = new SendSmtpEmailSender();

        sender.setEmail(configuration.getSenderEmail());
        sender.setName(configuration.getSenderName());
        sendSmtpEmail.setSender(sender);

        final List<SendSmtpEmailTo> recipients = email.getRecipients().stream()
                .map(recipient -> new SendSmtpEmailTo().email(recipient)).toList();

        sendSmtpEmail.setTo(recipients);
        sendSmtpEmail.setSubject(email.getSubject());

        if (email.getTemplateId() != null) {
            sendSmtpEmail.setTemplateId(email.getTemplateId());
            sendSmtpEmail.setParams(email.getTemplateParams());
        } else {
            sendSmtpEmail.setTextContent(email.getTextPlain());
        }

        emailsApi.sendTransacEmail(sendSmtpEmail);
    }

    @Override
    public void setApiKey(String apiKey) {
        logger.debug("initialize sendinblue api key");
        ApiClient client = Configuration.getDefaultApiClient();

        client.setDebugging(true);

        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) client.getAuthentication("api-key");
        apiKeyAuth.setApiKey(apiKey);
        emailsApi = new TransactionalEmailsApi();
        emailsApi.setApiClient(client);
    }
    
}

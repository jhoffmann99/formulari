package io.jhoffmann.formulari.check;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.jhoffmann.formulari.exception.NotFoundException;
import io.jhoffmann.formulari.exception.ValidationException;
import io.jhoffmann.formulari.model.AbstractComponent;
import io.jhoffmann.formulari.template.TemplateEntity;
import io.jhoffmann.formulari.template.TemplateRepository;
import io.jhoffmann.formulari.util.EmailValidator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckService {
    private final CheckRepository checkRepository;
    private final CheckRecipientRepository checkRecipientRepository;
    private final TemplateRepository templateRepository;

    public CheckService(CheckRepository checkRepository, CheckRecipientRepository checkRecipientRepository,
            TemplateRepository templateRepository) {
        this.checkRepository = checkRepository;
        this.checkRecipientRepository = checkRecipientRepository;
        this.templateRepository = templateRepository;
    }

    public CheckEntity createCheck(String name, TransmissionType transmissionType, String templateName,
            List<CheckRecipientDto> recipients) {
        validateCheck(transmissionType, recipients);

        Optional<TemplateEntity> optTemplate = templateRepository.findByName(templateName);

        if (optTemplate.isEmpty()) {
            throw new EntityNotFoundException("Could not find the template for the provided templateName");
        }

        TemplateEntity template = optTemplate.get();

        CheckEntity check = new CheckEntity();

        check.setTemplate(template);
        check.setName(name);
        check.setTransmissionType(transmissionType);
        check.setExpectedReplies(recipients.size());

        final CheckEntity savedCheck = checkRepository.save(check);

        return savedCheck;
    }

    public List<CheckRecipientEntity> createCheckRecipients(CheckEntity check, List<CheckRecipientDto> recipients) {
        List<CheckRecipientEntity> checkRecipientEntities = new ArrayList<>();

        recipients.forEach(recipient -> {
            CheckRecipientEntity checkRecipient = new CheckRecipientEntity();
            checkRecipient.setFirstName(recipient.getFirstName());
            checkRecipient.setLastName(recipient.getLastName());
            checkRecipient.setSalutation(recipient.getSalutation());
            checkRecipient.setEmail(recipient.getEmail());
            checkRecipient.setMobilePhone(recipient.getMobilePhone());
            checkRecipient.setUid(UUID.randomUUID().toString());
            checkRecipient.setCheck(check);

            checkRecipientEntities.add(checkRecipient);
        });

        return checkRecipientRepository.saveAllAndFlush(checkRecipientEntities);
    }

    private void validateCheck(TransmissionType transmissionType, List<CheckRecipientDto> recipients) {
        switch (transmissionType) {
            case E_MAIL -> validateEmailCheck(recipients);
            case SMS -> validateSmsCheck(recipients);
            case LINK -> validateLinkCheck(recipients);
        }
    }

    private void validateLinkCheck(List<CheckRecipientDto> recipients) {
        // TODO add implementation
    }

    private void validateSmsCheck(List<CheckRecipientDto> recipients) {
        boolean isValid = recipients.stream().allMatch(recipient -> !recipient.getMobilePhone().isBlank());

        if (!isValid) {
            throw new ValidationException("At least one mobile phonenumber of the recipients is invalid");
        }
    }

    private void validateEmailCheck(List<CheckRecipientDto> recipients) {
        boolean isValid = recipients.stream().allMatch(recipient -> EmailValidator.validate(recipient.getEmail()));

        if (!isValid) {
            throw new ValidationException("At least one email adress of the recipients is invalid");
        }
    }

    public void replyCheck(String uid, List<FieldReply> data) {
        Optional<CheckRecipientEntity> optCheckRecipient = checkRecipientRepository.findByUid(uid);

        if (optCheckRecipient.isEmpty()) {
            throw new NotFoundException();
        }

        CheckRecipientEntity checkRecipient = optCheckRecipient.get();

        if (checkRecipient.isCompleted()) {
            throw new RuntimeException("check already replied");
       } 

        CheckEntity check = checkRecipient.getCheck();

        TemplateEntity template = check.getTemplate();

        List<? extends AbstractComponent> templateFields = template.getComponents().getComponents();

        validateCheckReply(templateFields, data);

        checkRecipient.setData(data);

        checkRecipientRepository.save(checkRecipient);

        check.increaseTotalReplies();
        checkRepository.save(check);
    }

    private void validateCheckReply(List<? extends AbstractComponent> templateFields, List<FieldReply> data) {
        if (templateFields.size() != data.size()) {
            throw new ValidationException("number of provided fields does not match the expected number of fields");
        }

        templateFields.forEach(templateField -> {
            int fieldMatch = 0;
            for (FieldReply fieldReply : data) {
                if (templateField.getName().equals(fieldReply.getName())) {
                    fieldMatch += 1;
                    switch (fieldReply.getType()) {
                        case NUMBER -> validateNumberFieldValue(fieldReply.getValue());
                        case TEXT -> validateTextFieldValue(fieldReply.getValue());
                        case DATE -> validateDateFieldValue(fieldReply.getValue());
                    }
                }
            }
            if (fieldMatch != 1) {
                throw new ValidationException("the field either exists more than once or even does not exist at all");
            }
        });
    }

    private void validateDateFieldValue(Object value) {
        if (!(value instanceof LocalDate)) {
            throw new ValidationException("provided value is not of type LocalDate");
        }
    }

    private void validateTextFieldValue(Object value) {
        if (!(value instanceof String)) {
            throw new ValidationException("provided value is not of type String");
        }
    }

    private void validateNumberFieldValue(Object value) {
        if (!(value instanceof Number)) {
            throw new ValidationException("provided value is not of type Number");
        }
    }

}

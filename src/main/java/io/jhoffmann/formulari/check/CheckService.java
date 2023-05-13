package io.jhoffmann.formulari.check;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.jhoffmann.formulari.exception.ValidationException;
import io.jhoffmann.formulari.template.TemplateEntity;
import io.jhoffmann.formulari.template.TemplateRepository;
import io.jhoffmann.formulari.util.EmailValidator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckService {
    private final CheckRepository checkRepository;
    private final CheckRecipientRepository checkRecipientRepository;
    private final TemplateRepository templateRepository;
    

    public CheckService(CheckRepository checkRepository, CheckRecipientRepository checkRecipientRepository, TemplateRepository templateRepository) {
        this.checkRepository = checkRepository;
        this.checkRecipientRepository = checkRecipientRepository;
        this.templateRepository = templateRepository;
    }

    public void createCheck(String name, TransmissionType transmissionType, String templateName,
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

        final CheckEntity savedCheck = checkRepository.save(check);

        List<CheckRecipientEntity> checkRecipientEntities = new ArrayList<>();

        recipients.forEach(recipient -> {
            CheckRecipientEntity checkRecipient = new CheckRecipientEntity();
            checkRecipient.setFirstName(recipient.getFirstName());
            checkRecipient.setLastName(recipient.getLastName());
            checkRecipient.setSalutation(recipient.getSalutation());
            checkRecipient.setEmail(recipient.getEmail());
            checkRecipient.setMobilePhone(recipient.getMobilePhone());
            checkRecipient.setCheck(savedCheck);
            
            checkRecipientEntities.add(checkRecipient);
        });
        
        checkRecipientRepository.saveAllAndFlush(checkRecipientEntities);

    }

    private void validateCheck(TransmissionType transmissionType, List<CheckRecipientDto> recipients) {
        switch (transmissionType) {
            case E_MAIL -> validateEmailCheck(recipients);
            case SMS -> validateSmsCheck(recipients);
        }
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
    

}

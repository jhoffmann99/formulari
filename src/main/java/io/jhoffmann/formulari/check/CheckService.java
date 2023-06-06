package io.jhoffmann.formulari.check;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jhoffmann.formulari.auth.User;
import io.jhoffmann.formulari.auth.UserService;
import io.jhoffmann.formulari.email.Email;
import io.jhoffmann.formulari.email.EmailService;
import io.jhoffmann.formulari.exception.NotFoundException;
import io.jhoffmann.formulari.exception.ValidationException;
import io.jhoffmann.formulari.model.AbstractComponent;
import io.jhoffmann.formulari.model.DateField;
import io.jhoffmann.formulari.model.NumberField;
import io.jhoffmann.formulari.model.TextField;
import io.jhoffmann.formulari.model.YesNoField;
import io.jhoffmann.formulari.template.TemplateEntity;
import io.jhoffmann.formulari.template.TemplateRepository;
import io.jhoffmann.formulari.util.EmailValidator;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckService {
    private final CheckRepository checkRepository;
    private final CheckRecipientRepository checkRecipientRepository;
    private final TemplateRepository templateRepository;
    private final EmailService emailService;
    private final UserService userService;

    public CheckService(CheckRepository checkRepository, CheckRecipientRepository checkRecipientRepository,
            TemplateRepository templateRepository, EmailService emailService, UserService userService) {
        this.checkRepository = checkRepository;
        this.checkRecipientRepository = checkRecipientRepository;
        this.templateRepository = templateRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    public CheckEntity createCheck(String name, TransmissionType transmissionType, String templateUid,
            List<CheckRecipientDto> recipients, String subject, String greeting, UserDetails userDetails) {
        validateCheck(transmissionType, recipients);

        Optional<TemplateEntity> optTemplate = templateRepository.findByUid(templateUid);

        if (optTemplate.isEmpty()) {
            throw new EntityNotFoundException("Could not find the template for the provided templateName");
        }

        TemplateEntity template = optTemplate.get();

        Optional<User> optUser = userService.findUserByEmail(userDetails.getUsername());

        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optUser.get();

        CheckEntity check = new CheckEntity();

        check.setTemplate(template);
        check.setName(name);
        check.setTransmissionType(transmissionType);
        check.setExpectedReplies(recipients.size());
        check.setSubject(subject);
        check.setGreeting(greeting);
        check.setUser(user);

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

    public void answerCheck(String uid, List<FieldReply> data) {
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
        checkRecipient.setAnswerAt(LocalDateTime.now());

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
                    if (templateField instanceof TextField) {
                        validateTextFieldValue(fieldReply.getValue());
                    } else if (templateField instanceof DateField) {
                        validateDateFieldValue(fieldReply.getValue());
                    } else if (templateField instanceof NumberField) {
                        validateNumberFieldValue(fieldReply.getValue());
                    } else if (templateField instanceof YesNoField) {
                        validateYesNoFieldValue(fieldReply.getValue());
                    }
                }
            }
            if (fieldMatch != 1) {
                throw new ValidationException("the field either exists more than once or even does not exist at all");
            }
        });
    }

    private void validateYesNoFieldValue(Object value) {
        if (!(value instanceof String || value.equals("ja") || value.equals("nein"))) {
            throw new ValidationException("provided value is not valid");
        }
    }

    private void validateDateFieldValue(Object value) {

        try {
            LocalDate.parse((CharSequence) value);
        } catch (Exception e) {
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

    public void informCheckRecipients(List<CheckRecipientEntity> checkRecipients) {
        checkRecipients.parallelStream().forEach(recipient -> sendCheckEmail(recipient));
    }

    private void sendCheckEmail(CheckRecipientEntity checkRecipient) {
        Email email = new Email();
        email.setRecipients(List.of(checkRecipient.getEmail()));
        email.setSubject("formulari Check");
        email.setTextPlain(checkRecipient.getUid());

        System.out.println(checkRecipient.getUid());
        // emailService.sendEmail(email);
    }

    public void sendCheckReminder(CheckRecipientEntity checkRecipient) {
        Email email = new Email();
        email.setRecipients(List.of(checkRecipient.getEmail()));
        email.setSubject("Erinnerung");
        email.setTextPlain(checkRecipient.getUid());

        System.out.println(checkRecipient.getUid());
        emailService.sendEmail(email);
    }

    public Optional<CheckRecipientEntity> findCheckRecipientByUid(String uid) {
        return checkRecipientRepository.findByUid(uid);
    }

    public TemplateEntity getTemplateForCheckRecipientUid(String uid) {
        Optional<CheckRecipientEntity> optCheckRecipient = this.findCheckRecipientByUid(uid);

        if (optCheckRecipient.isEmpty()) {
            throw new NotFoundException("Datensatz nicht gefunden");
        }

        CheckRecipientEntity checkRecipient = optCheckRecipient.get();

        TemplateEntity template = checkRecipient.getCheck().getTemplate();

        return template;
    }

    public List<CheckRecipientEntity> getCheckReplies(UserDetails userDetails) {
        Optional<User> optUser = userService.findUserByEmail(userDetails.getUsername());

        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optUser.get();

        return checkRecipientRepository.findReplies(user.getSub());
    }

    public List<CheckRecipientEntity> getOpenCheckReplies(UserDetails userDetails) {
        Optional<User> optUser = userService.findUserByEmail(userDetails.getUsername());

        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optUser.get();

        return checkRecipientRepository.findOpenReplies(user.getSub());
    }

    public List<CheckRecipientEntity> getArchivedCheckReplies(UserDetails userDetails) {
        Optional<User> optUser = userService.findUserByEmail(userDetails.getUsername());

        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optUser.get();

        return checkRecipientRepository.findArchivedReplies(user.getSub());
    }

    public Optional<CheckEntity> findCheckById(Long checkId) {
        return checkRepository.findById(checkId);
    }

}

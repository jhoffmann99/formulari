package io.jhoffmann.formulari.template;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jhoffmann.formulari.auth.User;
import io.jhoffmann.formulari.auth.UserService;
import io.jhoffmann.formulari.exception.NotFoundException;
import io.jhoffmann.formulari.model.AbstractComponent;

@Service
public class TemplateService {
    private final TemplateRepository repository;
    private final UserService userService;

    public TemplateService(TemplateRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public TemplateEntity createTemplate(String templateName, List<? extends AbstractComponent> components,
            UserDetails userDetails) {
        Optional<User> optUser = userService.findUserByUsername(userDetails.getUsername());

        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optUser.get();

        TemplateEntity template = new TemplateEntity();
        template.setName(templateName);
        template.setUser(user);

        ComponentWrapper components2 = new ComponentWrapper();
        components2.setComponents(components);

        template.setComponents(components2);

        return repository.save(template);
    }

    public Optional<TemplateEntity> findTemplateByUid(String uid) {
        return repository.findByUid(uid);
    }

    public List<String> findAll() {
        return repository.findAll().stream().map(TemplateEntity::getName).toList();
    }

    public List<TemplateEntity> findAllByUser(UserDetails userDetails) {
        return repository.findByUsername(userDetails.getUsername());
    }

    public void deleteByUid(String uid, String username) {
        Optional<TemplateEntity> optTemplate = repository.findByUid(uid);

        if (optTemplate.isPresent()) {
            TemplateEntity template = optTemplate.get();
            if (template.getUser().getUsername().equals(username)) {
                template.setStatus(TemplateStatus.ARCHIVED);

                repository.save(template);
            }
            
        }
    }

}

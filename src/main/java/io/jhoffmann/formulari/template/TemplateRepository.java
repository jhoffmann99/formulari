package io.jhoffmann.formulari.template;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {

    Optional<TemplateEntity> findByName(String name);
    
}

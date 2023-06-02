package io.jhoffmann.formulari.template;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {
    
    @Query("SELECT t FROM TemplateEntity t LEFT JOIN t.user u WHERE t.status = 'ACTIVE' AND u.username = :username")
    List<TemplateEntity> findByUsername(@Param("username") String username);

    Optional<TemplateEntity> findByUid(String uid);


}

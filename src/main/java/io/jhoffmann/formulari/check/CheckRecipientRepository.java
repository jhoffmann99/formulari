package io.jhoffmann.formulari.check;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRecipientRepository extends JpaRepository<CheckRecipientEntity, Long> {
    Optional<CheckRecipientEntity> findByUid(String uid);
}

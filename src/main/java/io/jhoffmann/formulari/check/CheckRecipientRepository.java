package io.jhoffmann.formulari.check;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRecipientRepository extends JpaRepository<CheckRecipientEntity, Long> {
    
}

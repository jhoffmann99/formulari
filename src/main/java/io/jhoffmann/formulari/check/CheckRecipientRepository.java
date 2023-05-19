package io.jhoffmann.formulari.check;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CheckRecipientRepository extends JpaRepository<CheckRecipientEntity, Long> {
    Optional<CheckRecipientEntity> findByUid(String uid);

    @Query("SELECT r FROM CheckRecipientEntity r WHERE data is not null")
    List<CheckRecipientEntity> findReplies();
}

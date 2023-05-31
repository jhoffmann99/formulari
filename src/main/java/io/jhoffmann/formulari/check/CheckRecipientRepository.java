package io.jhoffmann.formulari.check;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckRecipientRepository extends JpaRepository<CheckRecipientEntity, Long> {
    Optional<CheckRecipientEntity> findByUid(String uid);

    @Query("SELECT r FROM CheckRecipientEntity r LEFT JOIN r.check c LEFT JOIN c.user u WHERE c.status = 'REQUESTED' AND r.data is not null AND u.username = :username")
    List<CheckRecipientEntity> findReplies(@Param("username") String username);

    @Query("SELECT r FROM CheckRecipientEntity r LEFT JOIN r.check c LEFT JOIN c.user u WHERE c.status = 'REQUESTED' AND r.data is null AND u.username = :username")
    List<CheckRecipientEntity> findOpenReplies(@Param("username") String username);

    @Query("SELECT r FROM CheckRecipientEntity r LEFT JOIN r.check c LEFT JOIN c.user u WHERE c.status = 'ARCHIVED' AND u.username = :username")
    List<CheckRecipientEntity> findArchivedReplies(@Param("username") String username);
}

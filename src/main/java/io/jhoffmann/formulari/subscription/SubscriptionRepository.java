package io.jhoffmann.formulari.subscription;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.jhoffmann.formulari.auth.User;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query("SELECT s FROM SubscriptionEntity s WHERE s.status = 'ACTIVE' AND s.user = :user ORDER BY s.activatedAt DESC LIMIT 1")
    Optional<SubscriptionEntity> findActiveSubscription(@Param("user") User user);

}

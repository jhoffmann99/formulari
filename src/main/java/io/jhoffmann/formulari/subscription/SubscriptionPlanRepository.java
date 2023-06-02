package io.jhoffmann.formulari.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlanEntity, Long> {
    
}

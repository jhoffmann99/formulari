package io.jhoffmann.formulari.check;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<CheckEntity, Long>{
    
}

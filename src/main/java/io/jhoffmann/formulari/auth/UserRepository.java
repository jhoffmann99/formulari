package io.jhoffmann.formulari.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findBySub(String username);

  Boolean existsBySub(String sub);

  Boolean existsByEmail(String email);

Optional<User> findByEmail(String email);
}
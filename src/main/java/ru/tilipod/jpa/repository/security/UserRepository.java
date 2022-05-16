package ru.tilipod.jpa.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tilipod.jpa.entity.security.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUuid(UUID userUuid);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}

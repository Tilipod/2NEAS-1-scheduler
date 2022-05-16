package ru.tilipod.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.tilipod.controller.dto.UserRegisterDto;
import ru.tilipod.jpa.entity.security.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    Optional<User> findByUuid(UUID userUuid);

    Optional<User> findByUsername(String username);

    User createUser(UserRegisterDto registerDto);

    User updateUser(User user);

    void addRole(UUID userUuid, String role);

    void deleteRole(UUID userUuid, String role);
}

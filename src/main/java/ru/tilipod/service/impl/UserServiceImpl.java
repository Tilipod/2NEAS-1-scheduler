package ru.tilipod.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tilipod.controller.dto.UserRegisterDto;
import ru.tilipod.exception.EntityNotFoundException;
import ru.tilipod.exception.UserAlreadyExistException;
import ru.tilipod.jpa.entity.security.Role;
import ru.tilipod.jpa.entity.security.User;
import ru.tilipod.jpa.repository.security.RoleRepository;
import ru.tilipod.jpa.repository.security.UserRepository;
import ru.tilipod.service.UserService;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUuid(UUID userUuid) {
        return userRepository.findByUuid(userUuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User createUser(UserRegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserAlreadyExistException(String.format("User with username = %s already exist", registerDto.getUsername()));
        }

        User user = new User();

        user.setUuid(UUID.randomUUID());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setRoles(Set.of(registerDto.getRole()));

        log.info("Create new user with uuid = {}, role = {}", user.getUuid(), registerDto.getRole());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void addRole(UUID userUuid, String role) {
        User user = findByUuid(userUuid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with uuid = %s not found", userUuid), User.class));

        Role roleEntity = roleRepository.findByName(role)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role with name = %s not found", role), Role.class));

        user.getRoles().add(roleEntity);

        log.info("For user with uuid = {} added role {}", userUuid, role);

        updateUser(user);
    }

    @Override
    @Transactional
    public void deleteRole(UUID userUuid, String role) {
        User user = findByUuid(userUuid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with uuid = %s not found", userUuid), User.class));

        Role roleEntity = roleRepository.findByName(role)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role with name = %s not found", role), Role.class));

        if (user.getRoles().contains(roleEntity)) {
            user.getRoles().remove(roleEntity);
            log.info("For user with uuid = {} deleted role {}", userUuid, role);
        }

        updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

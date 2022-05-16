package ru.tilipod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tilipod.controller.dto.UserRegisterDto;
import ru.tilipod.exception.EntityNotFoundException;
import ru.tilipod.jpa.entity.security.User;
import ru.tilipod.service.UserService;

import java.util.UUID;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(description = "Контроллер для работы с пользователями")
public class UserController {

    private final UserService userService;

    @GetMapping("/{username}/by-username")
    @ApiOperation(value = "Получить информацию о пользователе по его имени")
    public User findUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with username = %s not found", username), User.class));
    }

    @GetMapping("/{uuid}/by-uuid")
    @ApiOperation(value = "Получить информацию о пользователе по его uuid")
    public User findUserByUuid(@PathVariable UUID uuid) {
        return userService.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with uuid = %s not found", uuid), User.class));
    }

    @PostMapping("/register")
    @ApiOperation(value = "Зарегистрировать пользователя")
    public User register(@RequestBody UserRegisterDto registerDto) {
        return userService.createUser(registerDto);
    }

    @PutMapping("/{uuid}/role")
    @ApiOperation(value = "Добавить роль пользователю")
    public ResponseEntity<Void> addRole(@PathVariable UUID uuid, @RequestParam String role) {
        userService.addRole(uuid, role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}/role")
    @ApiOperation(value = "Удалить роль пользователю")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID uuid, @RequestParam String role) {
        userService.deleteRole(uuid, role);
        return ResponseEntity.ok().build();
    }

}

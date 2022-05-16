package ru.tilipod.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.tilipod.jpa.entity.security.Role;

@Data
@ApiModel(value = "UserRegister", description = "Информация для регистрации пользователя")
public class UserRegisterDto {

    @ApiModelProperty(name = "Имя пользователя")
    private String username;

    @ApiModelProperty(name = "Пароль пользователя")
    private String password;

    @ApiModelProperty(name = "Адрес почты пользователя")
    private String email;

    @ApiModelProperty(name = "Роль пользователя")
    private Role role;
}

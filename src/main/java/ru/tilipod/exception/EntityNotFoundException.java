package ru.tilipod.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private final Class<?> entityClass;

    public EntityNotFoundException(String message, Class<?> entityClass) {
        super(message);
        this.entityClass = entityClass;
    }

}

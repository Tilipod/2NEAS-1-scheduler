package ru.tilipod.exception;

import lombok.Getter;

@Getter
public class InvalidDataException extends RuntimeException {

    private final Object object;

    public InvalidDataException(String message, Object object) {
        super(message);
        this.object = object;
    }
}

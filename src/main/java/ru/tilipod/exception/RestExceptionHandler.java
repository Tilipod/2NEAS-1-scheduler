package ru.tilipod.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResult> handleEntityNotFoundException(EntityNotFoundException ex) {
        String message = ex.getMessage().concat(String.format(" Класс: %s", ex.getEntityClass().getSimpleName()));
        log.debug(message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResult.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND.value())
                .build()
        );
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResult> handleInvalidDataException(InvalidDataException ex) {
        String message = ex.getMessage().concat(String.format(" Класс: %s", ex.getObject().getClass().getSimpleName()));
        log.debug(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResult.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .build()
        );
    }

}

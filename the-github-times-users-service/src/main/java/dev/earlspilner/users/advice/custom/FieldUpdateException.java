package dev.earlspilner.users.advice.custom;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class FieldUpdateException extends RuntimeException {
    public FieldUpdateException(String message) {
        super(message);
    }
}

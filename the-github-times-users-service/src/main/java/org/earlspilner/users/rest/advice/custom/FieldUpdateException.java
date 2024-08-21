package org.earlspilner.users.rest.advice.custom;

public class FieldUpdateException extends RuntimeException {
    public FieldUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldUpdateException(String message) {
        super(message);
    }
}

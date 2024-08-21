package org.earlspilner.users.rest.advice.custom;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String mesasge, Throwable cause) {
        super(mesasge, cause);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}

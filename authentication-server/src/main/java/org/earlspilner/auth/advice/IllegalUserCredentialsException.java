package org.earlspilner.auth.advice;

public class IllegalUserCredentialsException extends RuntimeException {
    public IllegalUserCredentialsException(String message) {
        super(message);
    }
}

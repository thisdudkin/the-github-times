package org.earlspilner.newspaper.rest.advice.custom;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}

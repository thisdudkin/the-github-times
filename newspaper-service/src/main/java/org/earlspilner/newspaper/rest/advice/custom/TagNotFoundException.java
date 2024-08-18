package org.earlspilner.newspaper.rest.advice.custom;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String message) {
        super(message);
    }
}

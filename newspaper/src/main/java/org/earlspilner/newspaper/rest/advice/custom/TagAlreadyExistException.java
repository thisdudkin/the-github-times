package org.earlspilner.newspaper.rest.advice.custom;

public class TagAlreadyExistException extends RuntimeException {
    public TagAlreadyExistException(String message) {
        super(message);
    }
}

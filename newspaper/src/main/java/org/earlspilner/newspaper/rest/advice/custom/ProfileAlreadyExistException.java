package org.earlspilner.newspaper.rest.advice.custom;

/**
 * @author Alexander Dudkin
 */
public class ProfileAlreadyExistException extends RuntimeException {
    public ProfileAlreadyExistException(String msg) {
        super(msg);
    }
}

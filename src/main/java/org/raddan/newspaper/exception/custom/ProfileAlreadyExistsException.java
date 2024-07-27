package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class ProfileAlreadyExistsException extends RuntimeException {
    public ProfileAlreadyExistsException(String message) {
        super(message);
    }
}

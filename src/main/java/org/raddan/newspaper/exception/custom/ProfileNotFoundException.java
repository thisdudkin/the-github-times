package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}

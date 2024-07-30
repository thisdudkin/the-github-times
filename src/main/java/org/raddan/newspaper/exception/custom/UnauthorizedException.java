package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}

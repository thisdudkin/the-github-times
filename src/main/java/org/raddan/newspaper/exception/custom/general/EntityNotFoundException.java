package org.raddan.newspaper.exception.custom.general;

/**
 * @author Alexander Dudkin
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}

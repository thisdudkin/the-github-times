package org.raddan.newspaper.exception.custom.general;

/**
 * @author Alexander Dudkin
 */
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }
}

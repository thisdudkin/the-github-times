package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityAlreadyExistsException;

/**
 * @author Alexander Dudkin
 */
public class ProfileAlreadyExistsException extends EntityAlreadyExistsException {
    public ProfileAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public ProfileAlreadyExistsException(String msg) {
        super(msg);
    }
}

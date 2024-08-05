package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityAlreadyExistsException;

/**
 * @author Alexander Dudkin
 */
public class UserAlreadyExistsWithThatEmailException extends EntityAlreadyExistsException {
    public UserAlreadyExistsWithThatEmailException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserAlreadyExistsWithThatEmailException(String msg) {
        super(msg);
    }
}

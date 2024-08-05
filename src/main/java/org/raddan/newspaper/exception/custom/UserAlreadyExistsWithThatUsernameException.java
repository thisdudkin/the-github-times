package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityAlreadyExistsException;

/**
 * @author Alexander Dudkin
 */
public class UserAlreadyExistsWithThatUsernameException extends EntityAlreadyExistsException {
    public UserAlreadyExistsWithThatUsernameException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserAlreadyExistsWithThatUsernameException(String msg) {
        super(msg);
    }
}

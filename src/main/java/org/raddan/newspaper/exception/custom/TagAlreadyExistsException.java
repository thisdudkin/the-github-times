package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityAlreadyExistsException;

/**
 * @author Alexander Dudkin
 */
public class TagAlreadyExistsException extends EntityAlreadyExistsException {
    public TagAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public TagAlreadyExistsException(String msg) {
        super(msg);
    }
}

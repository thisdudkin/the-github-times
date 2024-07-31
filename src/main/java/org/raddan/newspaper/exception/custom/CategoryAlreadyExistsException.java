package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityAlreadyExistsException;

/**
 * @author Alexander Dudkin
 */
public class CategoryAlreadyExistsException extends EntityAlreadyExistsException {
    public CategoryAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public CategoryAlreadyExistsException(String msg) {
        super(msg);
    }
}

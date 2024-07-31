package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityNotFoundException;

/**
 * @author Alexander Dudkin
 */
public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CategoryNotFoundException(String msg) {
        super(msg);
    }
}

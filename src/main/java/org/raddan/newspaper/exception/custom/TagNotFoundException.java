package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityNotFoundException;

/**
 * @author Alexander Dudkin
 */
public class TagNotFoundException extends EntityNotFoundException {
    public TagNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public TagNotFoundException(String msg) {
        super(msg);
    }
}

package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityNotFoundException;

/**
 * @author Alexander Dudkin
 */
public class ProfileNotFoundException extends EntityNotFoundException {
    public ProfileNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ProfileNotFoundException(String msg) {
        super(msg);
    }
}

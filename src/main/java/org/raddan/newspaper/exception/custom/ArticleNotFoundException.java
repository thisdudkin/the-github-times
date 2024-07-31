package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityNotFoundException;

/**
 * @author Alexander Dudkin
 */
public class ArticleNotFoundException extends EntityNotFoundException {
    public ArticleNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ArticleNotFoundException(String msg) {
        super(msg);
    }
}

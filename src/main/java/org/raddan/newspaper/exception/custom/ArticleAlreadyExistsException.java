package org.raddan.newspaper.exception.custom;

import org.raddan.newspaper.exception.custom.general.EntityAlreadyExistsException;

/**
 * @author Alexander Dudkin
 */
public class ArticleAlreadyExistsException extends EntityAlreadyExistsException {
    public ArticleAlreadyExistsException(String msg, Throwable t) {
        super(msg, t);
    }

    public ArticleAlreadyExistsException(String msg) {
        super(msg);
    }
}

package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class ArticleAlreadyExistsException extends RuntimeException {
    public ArticleAlreadyExistsException(String message) {
        super(message);
    }
}

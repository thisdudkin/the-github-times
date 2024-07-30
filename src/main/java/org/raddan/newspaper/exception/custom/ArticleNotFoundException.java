package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}

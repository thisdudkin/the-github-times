package org.earlspilner.newspaper.rest.advice.custom;

/**
 * @author Alexander Dudkin
 */
public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String msg) {
        super(msg);
    }
}

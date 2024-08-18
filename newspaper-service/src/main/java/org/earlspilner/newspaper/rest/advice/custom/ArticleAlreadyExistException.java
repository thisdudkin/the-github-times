package org.earlspilner.newspaper.rest.advice.custom;

/**
 * @author Alexander Dudkin
 */
public class ArticleAlreadyExistException extends RuntimeException {
    public ArticleAlreadyExistException(String msg) {
        super(msg);
    }
}

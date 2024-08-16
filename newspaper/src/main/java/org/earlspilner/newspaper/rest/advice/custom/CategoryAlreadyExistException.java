package org.earlspilner.newspaper.rest.advice.custom;

/**
 * @author Alexander Dudkin
 */
public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String msg) {
        super(msg);
    }
}

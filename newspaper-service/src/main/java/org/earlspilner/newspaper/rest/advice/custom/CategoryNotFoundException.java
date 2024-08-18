package org.earlspilner.newspaper.rest.advice.custom;

/**
 * @author Alexander Dudkin
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String msg) {
        super(msg);
    }
}

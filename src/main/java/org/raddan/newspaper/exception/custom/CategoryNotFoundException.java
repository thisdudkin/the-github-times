package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String msg) {
        super(msg);
    }
}

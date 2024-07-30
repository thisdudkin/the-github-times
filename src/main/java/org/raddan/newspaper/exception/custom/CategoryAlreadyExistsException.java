package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}

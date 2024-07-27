package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class FieldEmptyException extends RuntimeException {
    public FieldEmptyException(String message) {
        super(message);
    }
}

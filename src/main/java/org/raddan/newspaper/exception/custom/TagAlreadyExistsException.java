package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class TagAlreadyExistsException extends RuntimeException {
    public TagAlreadyExistsException(String msg) {
        super(msg);
    }
}

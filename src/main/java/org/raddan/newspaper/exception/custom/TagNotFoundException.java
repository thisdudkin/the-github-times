package org.raddan.newspaper.exception.custom;

/**
 * @author Alexander Dudkin
 */
public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String msg) {
        super(msg);
    }
}

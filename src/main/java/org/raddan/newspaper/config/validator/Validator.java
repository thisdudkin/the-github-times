package org.raddan.newspaper.config.validator;

/**
 * @author Alexander Dudkin
 */
public interface Validator<T> {
    void validate(T entity);
}

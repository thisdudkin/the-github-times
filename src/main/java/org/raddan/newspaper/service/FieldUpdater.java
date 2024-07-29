package org.raddan.newspaper.service;

/**
 * @author Alexander Dudkin
 */
public interface FieldUpdater<T, D> {
    Object update(T obj, D data);
}

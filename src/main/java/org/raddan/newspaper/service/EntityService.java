package org.raddan.newspaper.service;/**
 * @author Alexander Dudkin
 */
public interface EntityService<T, D> {
    T create (D dto);
    T get();
    T update (D dto);
    String delete();
}

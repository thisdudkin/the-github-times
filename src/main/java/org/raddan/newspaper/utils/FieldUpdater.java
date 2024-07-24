package org.raddan.newspaper.utils;

import java.util.Map;

/**
 * @author Alexander Dudkin
 */
public interface FieldUpdater<T> {
    void update(T entity, Map<String, Object> requestInfo);
}

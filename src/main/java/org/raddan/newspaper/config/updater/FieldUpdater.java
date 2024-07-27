package org.raddan.newspaper.config.updater;

import org.raddan.newspaper.dto.ProfileCreateRequest;

/**
 * @author Alexander Dudkin
 */
public interface FieldUpdater<T> {
    void update(T entity, ProfileCreateRequest requestInfo);
}

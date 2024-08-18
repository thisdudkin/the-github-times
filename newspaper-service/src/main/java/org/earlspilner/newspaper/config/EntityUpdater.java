package org.earlspilner.newspaper.config;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author Alexander Dudkin
 */
@Service
public class EntityUpdater {

    public <T> void updateEntity(T currentEntity, T updatedEntity) {
        if (currentEntity == null || updatedEntity == null) {
            throw new IllegalArgumentException("Entities cannot be null");
        }

        Field[] fields = currentEntity.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object newValue = field.get(updatedEntity);
                if (newValue != null) {
                    field.set(currentEntity, newValue);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + field.getName(), e);
            }
        }
    }

}

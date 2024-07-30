package org.raddan.newspaper.config.updater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Alexander Dudkin
 */
@Component
public class EntityFieldUpdater {

    private static final Logger log = LoggerFactory.getLogger(EntityFieldUpdater.class);

    public <T, D> void update(T entity, D data) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            for (Field dtoField : data.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                if (dtoField.getName().equals(field.getName())) {
                    try {
                        field.setAccessible(true);
                        field.set(entity, dtoField.get(data));
                    } catch (IllegalAccessException e) {
                        log.error("Error updating profile: {}", e.getMessage());
                        throw new RuntimeException("Failed to update profile", e);
                    }
                }
            }
        }

        log.info("User: updated profile successfully!");
    }

}

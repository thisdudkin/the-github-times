package org.earlspilner.users.config;

import org.earlspilner.users.rest.advice.custom.FieldUpdateException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Alexander Dudkin
 */
@Component
public class FieldUpdater {

    public <T, D> void update(T entity, D dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Field entityField;
                try {
                    entityField = entity.getClass().getDeclaredField(field.getName());
                } catch (NoSuchFieldException e) {
                    continue;
                }

                if (entityField.getType().equals(field.getType())) {
                    entityField.setAccessible(true);
                    entityField.set(entity, field.get(dto));
                }
            } catch (IllegalAccessException e) {
                throw new FieldUpdateException("Error while updating field: " + field.getName(), e);
            }
        }
    }

}

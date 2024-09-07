package dev.earlspilner.articles.config;

import dev.earlspilner.articles.advice.FieldUpdateException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alexander Dudkin
 */
@Component
public class FieldUpdater {

    private final Map<Class<?>, Map<String, Field>> cachedFields = new ConcurrentHashMap<>();

    public <T, D> void update(T targetEntity, D dto) {
        Map<String, Field> entityFields = getCachedFields(targetEntity.getClass());
        Map<String, Field> dtoFields = getCachedFields(dto.getClass());

        for (Map.Entry<String, Field> dtoFieldEntry : dtoFields.entrySet()) {
            String fieldName = dtoFieldEntry.getKey();
            Field dtoField = dtoFieldEntry.getValue();
            Field entityField = entityFields.get(fieldName);

            if (entityField == null) {
                continue;
            }

            if (entityField.getType().equals(dtoField.getType())) {
                try {
                    dtoField.setAccessible(true);
                    Object value = dtoField.get(dto);

                    if (value != null) {
                        entityField.setAccessible(true);
                        entityField.set(targetEntity, value);
                    }
                } catch (IllegalAccessException e) {
                    throw new FieldUpdateException("Error while updating field: " + fieldName);
                }
            }
        }
    }

    private Map<String, Field> getCachedFields(Class<?> clazz) {
        return cachedFields.computeIfAbsent(clazz, key -> {
            Map<String, Field> fieldsMap = new ConcurrentHashMap<>();
            for (Field field : key.getDeclaredFields()) {
                fieldsMap.put(field.getName(), field);
            }
            return fieldsMap;
        });
    }

}

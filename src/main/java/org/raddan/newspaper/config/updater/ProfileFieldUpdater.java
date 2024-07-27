package org.raddan.newspaper.config.updater;

import org.raddan.newspaper.dto.ProfileCreateRequest;
import org.raddan.newspaper.entity.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Alexander Dudkin
 */
@Component
public class ProfileFieldUpdater implements FieldUpdater<Profile> {

    private static final Logger logger = LoggerFactory.getLogger(ProfileFieldUpdater.class);

    @Override
    public void update(Profile entity, ProfileCreateRequest requestInfo) {
        Field[] fields = ProfileCreateRequest.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(requestInfo);
                if (value != null) {
                    Field profileField = entity.getClass().getDeclaredField(field.getName());
                    profileField.setAccessible(true);
                    profileField.set(entity, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("Error accessing field {}", field.getName(), e);
            }
        }
    }
}

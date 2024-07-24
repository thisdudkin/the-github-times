package org.raddan.newspaper.utils;

import org.raddan.newspaper.entity.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Alexander Dudkin
 */
@Component
public class ProfileFieldUpdater implements FieldUpdater<Profile> {

    private static final Logger logger = LoggerFactory.getLogger(ProfileFieldUpdater.class);

    @Override
    public void update(Profile profile, Map<String, Object> requestInfo) {
        requestInfo.forEach((fieldName, newValue) -> {
            try {
                Field field = profile.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(profile, newValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                logger.error("Error while updating profile field {}", fieldName, e);
            }
        });
    }
}

package org.raddan.newspaper.service;

import org.raddan.newspaper.dto.ProfileDTO;
import org.raddan.newspaper.entity.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Alexander Dudkin
 */
@Component
public class ProfileFieldUpdater implements FieldUpdater<Profile, ProfileDTO> {

    private static final Logger log = LoggerFactory.getLogger(ProfileFieldUpdater.class);

    @Override
    public Object update(Profile profile, ProfileDTO data) {
        for (Field field : profile.getClass().getDeclaredFields()) {
            for (Field dtoField : data.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                if (dtoField.getName().equals(field.getName())) {
                    try {
                        field.setAccessible(true);
                        field.set(profile, dtoField.get(data));
                    } catch (IllegalAccessException e) {
                        log.error("Error updating profile: {}", e.getMessage());
                        throw new RuntimeException("Failed to update profile", e);
                    }
                }
            }
        }

        log.info("User: '{}' updated profile successfully!", profile.getUser().getId());
        return profile;
    }

}

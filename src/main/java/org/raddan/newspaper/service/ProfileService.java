package org.raddan.newspaper.service;

import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.ProfileDto;
import org.raddan.newspaper.model.Profile;
import org.raddan.newspaper.model.User;
import org.raddan.newspaper.exception.custom.ProfileAlreadyExistsException;
import org.raddan.newspaper.exception.custom.ProfileNotFoundException;
import org.raddan.newspaper.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author Alexander Dudkin
 */
@Service
public class ProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EntityFieldUpdater fieldUpdater;

    @Autowired
    private UserService userService;

    public Profile getProfile() {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() == null) {
            LOG.warn("[Get] Profile not found for user: {}", currentUser.getUsername());
            throw new ProfileNotFoundException("You do not have a profile");
        }

        LOG.info("Profile retrieved for user: {}", currentUser.getUsername());
        return currentUser.getProfile();
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> {
                    LOG.warn("[GetByID] Profile not found for id: {}", id);
                    return new ProfileNotFoundException("Profile not found");
                });
    }

    public Profile getByUsername(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> {
                    LOG.warn("[GetByUsername] Profile not found for username {}", username);
                    return new ProfileNotFoundException("Profile not found");
                });
    }

    public Profile create(ProfileDto dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() != null) {
            LOG.warn("User {} already has a profile", currentUser.getUsername());
            throw new ProfileAlreadyExistsException("You already have a profile");
        }

        Profile profile = Profile.builder()
                .user(currentUser)
                .fullName(dto.getFullName())
                .avatar(dto.getAvatar())
                .bio(dto.getBio())
                .build();

        profileRepository.save(profile);
        LOG.info("Profile created for user: {}", currentUser.getUsername());
        return profile;
    }

    public Profile update(ProfileDto dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() == null) {
            LOG.warn("[Update] Profile not found for user: {}", currentUser.getUsername());
            throw new ProfileNotFoundException("You do not have a profile");
        }

        fieldUpdater.update(currentUser.getProfile(), dto);
        profileRepository.save(currentUser.getProfile());
        LOG.info("Profile updated for user: {}", currentUser.getUsername());
        return currentUser.getProfile();
    }

    public String delete() {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() == null) {
            LOG.warn("[Delete] Profile not found for user: {}", currentUser.getUsername());
            throw new ProfileNotFoundException("You do not have a profile");
        }

        profileRepository.delete(currentUser.getProfile());
        LOG.info("Profile deleted for user: {}", currentUser.getUsername());
        return "Profile deleted";
    }

}

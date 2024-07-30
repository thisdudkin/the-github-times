package org.raddan.newspaper.service;

import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.ProfileDTO;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.exception.custom.ProfileAlreadyExistsException;
import org.raddan.newspaper.exception.custom.ProfileNotFoundException;
import org.raddan.newspaper.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author Alexander Dudkin
 */
@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private EntityFieldUpdater fieldUpdater;

    @Autowired
    private UserService userService;

    public Profile create(ProfileDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() != null)
            throw new ProfileAlreadyExistsException("You already have a profile");

        Profile profile = Profile.builder()
                .user(currentUser)
                .fullName(dto.getFullName())
                .avatar(dto.getAvatar())
                .bio(dto.getBio())
                .createdUtc(Instant.now().getEpochSecond())
                .build();

        profileRepository.save(profile);
        return profile;
    }

    public Profile get() {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() == null)
            throw new ProfileNotFoundException("You do not have a profile");

        return currentUser.getProfile();
    }

    public Profile getByUsername(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException("You do not have a profile"));
    }

    public Profile update(ProfileDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() == null)
            throw new ProfileNotFoundException("You do not have a profile");

        fieldUpdater.update(currentUser.getProfile(), dto);
        profileRepository.save(currentUser.getProfile());
        return currentUser.getProfile();
    }

    public String delete() {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getProfile() == null)
            throw new ProfileNotFoundException("You do not have a profile");

        profileRepository.delete(currentUser.getProfile());
        return "Profile deleted";
    }

}

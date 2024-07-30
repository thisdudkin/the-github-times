package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.dto.ProfileDTO;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.exception.custom.ProfileAlreadyExistsException;
import org.raddan.newspaper.exception.custom.ProfileNotFoundException;
import org.raddan.newspaper.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Alexander Dudkin
 */
@Service
public class ProfileService implements EntityService<Profile, ProfileDTO> {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileFieldUpdater fieldUpdater;

    @Override
    @Transactional
    public Profile create(ProfileDTO dto) {
        User authorizedUser = userService.getCurrentUser();
        if (authorizedUser.getProfile() != null)
            throw new ProfileAlreadyExistsException("You already have a profile");

        Profile profile = Profile.builder()
                .user(authorizedUser)
                .fullName(dto.getFullName().trim())
                .avatar(dto.getAvatar().trim())
                .bio(dto.getBio().trim())
                .createdUtc(Instant.now().getEpochSecond())
                .build();

        profileRepository.save(profile);
        return profile;
    }

    @Override
    public Profile get() {
        User authorizedUser = userService.getCurrentUser();
        if (authorizedUser.getProfile() == null)
            throw new ProfileNotFoundException("You do not have a profile");

        return authorizedUser.getProfile();
    }

    public Profile getByUsername(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException("You do not have a profile"));
    }

    @Override
    @Transactional
    public Profile update(ProfileDTO dto) {
        User authorizedUser = userService.getCurrentUser();
        if (authorizedUser.getProfile() == null)
            throw new ProfileNotFoundException("You do not have a profile");

        fieldUpdater.update(authorizedUser.getProfile(), dto);
        profileRepository.save(authorizedUser.getProfile());
        return authorizedUser.getProfile();
    }

    @Override
    @Transactional
    public String delete() {
        User authorizedUser = userService.getCurrentUser();
        if (authorizedUser.getProfile() == null)
            throw new ProfileNotFoundException("You do not have a profile");

        Long id = authorizedUser.getProfile().getId();
        profileRepository.deleteById(id);
        return "Profile '" + id + "' has been deleted at: " + LocalDateTime.now();
    }

}

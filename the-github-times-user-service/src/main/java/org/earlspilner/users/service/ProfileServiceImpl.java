package org.earlspilner.users.service;

import jakarta.persistence.EntityNotFoundException;
import org.earlspilner.users.config.FieldUpdater;
import org.earlspilner.users.rest.advice.custom.ProfileNotFoundException;
import org.earlspilner.users.rest.advice.custom.ProfileServiceException;
import org.earlspilner.users.rest.dto.request.ProfileRequest;
import org.earlspilner.users.mapper.ProfileMapper;
import org.earlspilner.users.model.Profile;
import org.earlspilner.users.model.User;
import org.earlspilner.users.repository.ProfileRepository;
import org.earlspilner.users.rest.dto.response.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Alexander Dudkin
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;
    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final FieldUpdater fieldUpdater;

    @Autowired
    public ProfileServiceImpl(UserService userService, ProfileMapper profileMapper, ProfileRepository profileRepository, FieldUpdater fieldUpdater) {
        this.userService = userService;
        this.profileMapper = profileMapper;
        this.profileRepository = profileRepository;
        this.fieldUpdater = fieldUpdater;
    }

    @Override
    public Page<Profile> getProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public List<Profile> getProfiles(Sort sort) {
        return profileRepository.findAll(sort);
    }

    @Override
    public List<Profile> getProfiles() {
        long profileCount = profileRepository.count();
        if (profileCount == 0) {
            throw new ProfileNotFoundException("No profiles found");
        }
        return profileRepository.findAll();
    }

    @Override
    public Profile createProfile(ProfileRequest dto) {
        try {
            User authorizedUser = getAuthenticatedUser();
            Profile profile = profileMapper.toEntity(dto);
            profile.setUser(authorizedUser);
            return profileRepository.save(profile);
        } catch (RuntimeException ex) {
            throw new ProfileServiceException("Failed to create profile", ex);
        }
    }

    @Override
    public Profile getProfileById(int id) {
        return findProfileByIdOrThrow(id);
    }

    @Override
    public Profile updateProfile(int id, ProfileRequest dto) {
        try {
            Profile profile = findProfileByIdOrThrow(id);
            fieldUpdater.update(profile, dto);
            return profileRepository.save(profile);
        } catch (RuntimeException ex) {
            throw new ProfileServiceException("Failed to update profile", ex);
        }
    }

    @Override
    public void deleteProfileById(int id) {
        if (!profileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Profile not found for ID: " + id);
        }
        try {
            profileRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new ProfileServiceException("Failed to delete profile", ex);
        }
    }

    private User getAuthenticatedUser() {
        return userService.getAuthenticatedUser(SecurityContextHolder.getContext().getAuthentication());
    }

    private Profile findProfileByIdOrThrow(int id) {
        return profileRepository.findById(id)
                .orElseThrow(entityNotFoundSupplier(id));
    }

    private Supplier<? extends RuntimeException> entityNotFoundSupplier(int id) {
        return () -> new EntityNotFoundException("Profile not found for ID: " + id);
    }

}

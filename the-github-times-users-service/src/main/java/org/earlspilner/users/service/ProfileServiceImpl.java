package org.earlspilner.users.service;

import jakarta.persistence.EntityNotFoundException;
import org.earlspilner.users.config.FieldUpdater;
import org.earlspilner.users.dto.ProfileDto;
import org.earlspilner.users.mapper.ProfileMapper;
import org.earlspilner.users.model.Profile;
import org.earlspilner.users.model.User;
import org.earlspilner.users.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (profileRepository.count() != 0) {
            return profileRepository.findAll();
        } else {
            throw new EntityNotFoundException("There are zero profiles in the database");
        }
    }

    @Override
    public Profile createProfile(ProfileDto dto) {
        User authorizedUser = userService.getAuthenticatedUser(SecurityContextHolder.getContext().getAuthentication());

        Profile profile = profileMapper.toEntity(dto);
        profile.setUser(authorizedUser);
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileById(int id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for ID: " + id));
    }

    @Override
    public Profile updateProfile(int id, ProfileDto dto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found for ID: " + id));
        fieldUpdater.update(profile, dto);
        return profile;
    }

    @Override
    public void deleteProfileById(int id) {
        profileRepository.deleteById(id);
    }

}

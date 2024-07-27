package org.raddan.newspaper.service;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ProfileCreateRequest;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.exception.custom.ProfileAlreadyExistsException;
import org.raddan.newspaper.repository.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;

    /**
     * Service method to create a new profile
     * @param dto data transfer object stands for the personal information
     * @return {@code ResponseEntity<OK>} if profile created successfully
     * @throws ProfileAlreadyExistsException if authorized user already has an existing profile
     */
    public ResponseEntity<?> createProfile(ProfileCreateRequest dto) {
        User authorizedUser = userService.getCurrentUser();
        Optional<Profile> optionalProfile = profileRepository.findByUsername(authorizedUser.getUsername());
        if (optionalProfile.isPresent())
            throw new ProfileAlreadyExistsException("Profile already exists");

        Profile profile = Profile.builder()
                .user(authorizedUser)
                .fullName(dto.getFullName())
                .avatar(dto.getAvatar())
                .bio(dto.getBio())
                .build();

        profileRepository.save(profile);
        return new ResponseEntity<>("Profile just created.", OK);
    }
}

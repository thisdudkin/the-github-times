package org.raddan.newspaper.service;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.entity.dto.ProfileCreationRequest;
import org.raddan.newspaper.entity.response.ProfileCreationResponse;
import org.raddan.newspaper.exception.AlreadyExistsException;
import org.raddan.newspaper.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;

    public ProfileCreationResponse createProfile(ProfileCreationRequest request) {
        Optional<Profile> optionalProfile = profileRepository.findByUser(userService.getCurrentUser().getId());
        if (optionalProfile.isPresent()) {
            throw new AlreadyExistsException("You already have an existing profile..");
        }

        var profile = Profile.builder()
                .user(userService.getCurrentUser())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .bio(request.getBio())
                .createdUtc(Instant.now().getEpochSecond())
                .build();

        profileRepository.save(profile);

        return new ProfileCreationResponse(
                profile.getId(),
                request.getFirstName(),
                request.getLastName(),
                Instant.ofEpochSecond(profile.getCreatedUtc())
        );
    }
}

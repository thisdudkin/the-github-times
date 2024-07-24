package org.raddan.newspaper.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.entity.dto.ProfileRequest;
import org.raddan.newspaper.entity.response.creation.ProfileCreationResponse;
import org.raddan.newspaper.entity.response.info.ProfileInfoResponse;
import org.raddan.newspaper.exception.custom.AlreadyExistsException;
import org.raddan.newspaper.exception.custom.UnauthorizedException;
import org.raddan.newspaper.filter.DateFilter;
import org.raddan.newspaper.repository.ProfileRepository;
import org.raddan.newspaper.repository.UserRepository;
import org.raddan.newspaper.utils.ProfileFieldUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);
    private static final String http = "http://";
    private static final String profileUrl = http + "localhost:8080/profile";
    private static final String delimiter = "/";

    private final ProfileRepository profileRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ProfileFieldUpdater fieldUpdater;
    private final ProfileFieldUpdater profileFieldUpdater;

    public ProfileCreationResponse createProfile(ProfileRequest request) {
        User authorizedUser = userService.getCurrentUser();
        if (authorizedUser == null)
            throw new UnauthorizedException("You are not authorized to perform this action");
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
                .updatedUtc(Instant.now().getEpochSecond())
                .build();

        profileRepository.save(profile);

        return new ProfileCreationResponse(
                profile.getId(),
                profile.getFirstName(),
                profile.getLastName(),
                DateFilter.formatInstant(profile.getCreatedUtc())
        );

    }

    public ProfileInfoResponse getProfileInfo(String username) {
        Optional<Profile> optionalProfile = profileRepository.findByUsername(username);

        if (optionalProfile.isEmpty())
            throw new EntityNotFoundException("Profile not found..");

        return createProfileInfoResponse(optionalProfile.get());
    }

    public ProfileInfoResponse editProfileInfo(Map<String, Object> request) {
        Optional<Profile> optionalProfile = profileRepository.findByUser(userService.getCurrentUser().getId());
        if (optionalProfile.isEmpty())
            throw new EntityNotFoundException("Profile not found..");

        var profile = optionalProfile.get();

        profileFieldUpdater.update(profile, request);

        profile.setUpdatedUtc(Instant.now().getEpochSecond());
        profileRepository.save(profile);
        return createProfileInfoResponse(profile);
    }

    private ProfileInfoResponse createProfileInfoResponse(Profile profile) {
        return new ProfileInfoResponse(
                profile.getId(),
                profile.getUser().getUsername(),
                profile.getUser().getEmail(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getBio(),
                DateFilter.formatInstant(profile.getCreatedUtc()),
                DateFilter.formatInstant(profile.getUpdatedUtc())
        );
    }

    public Map<String, String> shareProfile(String username) {
        Map<String, String> shareUrl = new ConcurrentHashMap<>();
        User optionalUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        String url = profileUrl + delimiter + optionalUser.getUsername();
        shareUrl.put("url", url);

        return shareUrl;
    }
}

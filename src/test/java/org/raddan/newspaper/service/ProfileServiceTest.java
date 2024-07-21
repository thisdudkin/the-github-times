package org.raddan.newspaper.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.entity.dto.ProfileRequest;
import org.raddan.newspaper.entity.response.creation.ProfileCreationResponse;
import org.raddan.newspaper.entity.response.info.ProfileInfoResponse;
import org.raddan.newspaper.exception.custom.AlreadyExistsException;
import org.raddan.newspaper.repository.ProfileRepository;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfileService profileService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
    }

    @Test
    @Order(1)
    void createProfile_ShouldThrowAlreadyExistsException_WhenProfileExists() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(profileRepository.findByUser(user.getId())).thenReturn(Optional.of(new Profile()));

        var request = new ProfileRequest();
        request.setFirstName("First");
        request.setLastName("Last");
        request.setBio("Bio");

        assertThrows(AlreadyExistsException.class, () -> profileService.createProfile(request));
    }

    @Test
    @Order(2)
    void createProfile_ShouldReturnProfileCreationResponse_WhenProfileIsCreated() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(profileRepository.findByUser(user.getId())).thenReturn(Optional.empty());
        when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var request = new ProfileRequest();
        request.setFirstName("First");
        request.setLastName("Last");
        request.setBio("Bio");

        ProfileCreationResponse response = profileService.createProfile(request);

        assertNotNull(response);
        assertEquals("First", response.getFirstName());
        assertEquals("Last", response.getLastName());
    }

    @Test
    @Order(3)
    void getProfileInfo_ShouldThrowEntityNotFoundException_WhenProfileDoesNotExist() {
        when(profileRepository.findByUsername("nonexistentUser")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> profileService.getProfileInfo("nonexistentUser"));
    }

    @Test
    @Order(4)
    void getProfileInfo_ShouldReturnProfileResponse_WhenProfileExists() {
        var profile = new Profile();
        profile.setId(1L);
        profile.setUser(user);
        profile.setFirstName("First");
        profile.setLastName("Last");
        profile.setBio("Bio");
        profile.setCreatedUtc(Instant.now().getEpochSecond());
        profile.setUpdatedUtc(profile.getCreatedUtc());

        when(profileRepository.findByUsername("testUser")).thenReturn(Optional.of(profile));

        ProfileInfoResponse response = profileService.getProfileInfo("testUser");

        assertNotNull(response);
        assertEquals("First", response.getFirstName());
        assertEquals("Last", response.getLastName());
        assertEquals("Bio", response.getBio());
    }

    @Test
    @Order(5)
    void editProfile_ShouldThrowEntityNotFoundException_WhenProfileDoesNotExist() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(profileRepository.findByUser(user.getId())).thenReturn(Optional.empty());

        var request = new ProfileRequest();
        assertThrows(EntityNotFoundException.class, () -> profileService.editProfileInfo(request));
    }

    @Test
    @Order(6)
    void editProfileInfo_ShouldUpdateProfile_WhenProfileExists() {
        var existingProfile = new Profile();
        existingProfile.setId(1L);
        existingProfile.setUser(user);
        existingProfile.setFirstName("OldFirst");
        existingProfile.setLastName("OldLast");
        existingProfile.setBio("OldBio");
        existingProfile.setCreatedUtc(Instant.now().getEpochSecond());
        existingProfile.setUpdatedUtc(existingProfile.getCreatedUtc());

        when(userService.getCurrentUser()).thenReturn(user);

        when(profileRepository.findByUser(user.getId())).thenReturn(Optional.of(existingProfile));
        when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var request = new ProfileRequest();
        request.setFirstName("NewFirst");
        request.setLastName("NewLast");
        request.setBio("NewBio");

        ProfileInfoResponse response = profileService.editProfileInfo(request);

        assertNotNull(response);
        assertEquals("NewFirst", response.getFirstName());
        assertEquals("NewLast", response.getLastName());
        assertEquals("NewBio", response.getBio());
    }

}

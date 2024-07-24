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
import org.raddan.newspaper.utils.ProfileFieldUpdater;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProfileFieldUpdater profileFieldUpdater;

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

        Map<String, Object> requestInfo = new ConcurrentHashMap<>();
        assertThrows(EntityNotFoundException.class, () -> profileService.editProfileInfo(requestInfo));
    }

    @Test
    @Order(6)
    void editProfileInfo_ShouldUpdateProfile_WhenProfileExists() {
        // Arrange
        when(userService.getCurrentUser()).thenReturn(user);

        Profile existingProfile = new Profile();
        existingProfile.setId(1L);
        existingProfile.setUser(user);
        existingProfile.setFirstName("OldFirstName");
        existingProfile.setLastName("OldLastName");
        existingProfile.setBio("OldBio");
        existingProfile.setCreatedUtc(Instant.now().getEpochSecond());
        existingProfile.setUpdatedUtc(existingProfile.getCreatedUtc());

        when(profileRepository.findByUser(user.getId())).thenReturn(Optional.of(existingProfile));

        Map<String, Object> requestInfo = new ConcurrentHashMap<>();
        requestInfo.put("firstName", "NewFirstName");
        requestInfo.put("lastName", "NewLastName");
        requestInfo.put("bio", "NewBio");

        doAnswer(invocation -> {
            Profile profile = invocation.getArgument(0);
            Map<String, Object> info = invocation.getArgument(1);
            info.forEach((fieldName, newValue) -> {
                try {
                    Field field = profile.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(profile, newValue);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return null;
        }).when(profileFieldUpdater).update(any(Profile.class), anyMap());

        // Act
        ProfileInfoResponse response = profileService.editProfileInfo(requestInfo);

        // Assert
        assertNotNull(response);
        assertEquals("NewFirstName", response.getFirstName());
        assertEquals("NewLastName", response.getLastName());
        assertEquals("NewBio", response.getBio());

        verify(profileFieldUpdater, times(1)).update(existingProfile, requestInfo);
        verify(profileRepository, times(1)).save(existingProfile);
    }


}

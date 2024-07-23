package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.dto.ProfileRequest;
import org.raddan.newspaper.entity.response.info.ProfileInfoResponse;
import org.raddan.newspaper.entity.response.creation.ProfileCreationResponse;
import org.raddan.newspaper.service.ProfileService;
import org.raddan.newspaper.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @PostMapping(path = "/create")
    public ProfileCreationResponse createProfile(@RequestBody ProfileRequest request) {
        return profileService.createProfile(request);
    }

    @GetMapping(path = "/{username}")
    public ProfileInfoResponse getProfileInfo(@PathVariable String username) {
        return profileService.getProfileInfo(username);
    }

    @PutMapping(path = "/edit")
    public ProfileInfoResponse editProfileInfo(@RequestBody ProfileRequest request) {
        return profileService.editProfileInfo(request);
    }

    @PostMapping(path = "/{username}/share")
    public Map<String, String> shareProfile(@PathVariable String username) {
        return profileService.shareProfile(username);
    }

}

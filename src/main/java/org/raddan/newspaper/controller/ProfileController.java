package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ProfileDTO;
import org.raddan.newspaper.entity.Profile;
import org.raddan.newspaper.service.ProfileService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/new")
    public Profile createProfile(@RequestBody ProfileDTO dto) {
        return profileService.create(dto);
    }

    @GetMapping
    public Profile getProfile() {
        return profileService.get();
    }

    @PutMapping
    public Profile updateProfile(@RequestBody ProfileDTO dto) {
        return profileService.update(dto);
    }

    @DeleteMapping
    public String deleteProfile() {
        return profileService.delete();
    }

}

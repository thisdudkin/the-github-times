package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ProfileDTO;
import org.raddan.newspaper.model.Profile;
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

    @GetMapping
    public Profile getProfile() {
        return profileService.getProfile();
    }

    @GetMapping("/{id:[0-9]+}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @GetMapping("/{username:[a-zA-Z]+}")
    public Profile getProfileByUsername(@PathVariable String username) {
        return profileService.getByUsername(username);
    }

    @PostMapping("/create")
    public Profile createProfile(@RequestBody ProfileDTO dto) {
        return profileService.create(dto);
    }

    @PutMapping("/edit")
    public Profile updateProfile(@RequestBody ProfileDTO dto) {
        return profileService.update(dto);
    }

    @DeleteMapping("/delete")
    public String deleteProfile() {
        return profileService.delete();
    }

}

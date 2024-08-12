package org.raddan.newspaper.controller;

import org.raddan.newspaper.dto.ProfileDto;
import org.raddan.newspaper.model.Profile;
import org.raddan.newspaper.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping(path = "/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public Profile getProfile() {
        return profileService.getProfile();
    }

    @GetMapping("/{id:[0-9]+}")
    public Profile getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id);
    }

    @PostMapping("/create")
    public Profile createProfile(@RequestBody ProfileDto dto) {
        return profileService.create(dto);
    }

    @PutMapping("/edit")
    public Profile updateProfile(@RequestBody ProfileDto dto) {
        return profileService.update(dto);
    }

    @DeleteMapping("/delete")
    public String deleteProfile() {
        return profileService.delete();
    }

}

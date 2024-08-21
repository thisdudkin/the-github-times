package org.earlspilner.users.rest.controller;

import org.earlspilner.users.dto.ProfileDto;
import org.earlspilner.users.model.Profile;
import org.earlspilner.users.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<Page<Profile>> getProfiles(Pageable pageable) {
        Page<Profile> profiles = profileService.getProfiles(pageable);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping(params = "sort")
    public ResponseEntity<List<Profile>> getProfiles(@RequestParam Sort sort) {
        List<Profile> profiles = profileService.getProfiles(sort);
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable int id) {
        Profile profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileDto dto) {
        Profile profile = profileService.createProfile(dto);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable int id, @RequestBody ProfileDto dto) {
        Profile profile = profileService.updateProfile(id, dto);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        profileService.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }

}

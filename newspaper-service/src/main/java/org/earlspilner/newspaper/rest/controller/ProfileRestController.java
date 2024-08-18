package org.earlspilner.newspaper.rest.controller;

import org.earlspilner.newspaper.model.Profile;
import org.earlspilner.newspaper.service.NewspaperService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * @author Alexander Dudkin
 */
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/api/profiles")
public class ProfileRestController {

    private final NewspaperService newspaperService;

    @Autowired
    public ProfileRestController(NewspaperService newspaperService, ModelMapper modelMapper) {
        this.newspaperService = newspaperService;
    }

    @GetMapping
    public ResponseEntity<Collection<Profile>> getAllProfiles() {
        return ResponseEntity.ok(newspaperService.findAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable int id) {
        return ResponseEntity.ok(newspaperService.findProfileById(id));
    }

    @GetMapping("/search/by-first-name")
    public ResponseEntity<Collection<Profile>> getProfilesByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(newspaperService.findAllProfilesByFirstName(firstName));
    }

    @GetMapping("/search/by-last-name")
    public ResponseEntity<Collection<Profile>> getProfilesByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(newspaperService.findAllProfilesByLastName(lastName));
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(@RequestBody Profile profile) {
        newspaperService.updateProfile(profile);
        return ResponseEntity.ok("Profile successfully updated with ID: " + profile.getId());
    }

    @PostMapping
    public ResponseEntity<String> saveProfile(@RequestBody Profile profile) {
        newspaperService.saveProfile(profile);
        return ResponseEntity.ok("Profile successfully saved at: " + ZonedDateTime.now());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfile(@RequestBody Profile profile) {
        newspaperService.deleteProfile(profile);
        return ResponseEntity.noContent().build();
    }
}

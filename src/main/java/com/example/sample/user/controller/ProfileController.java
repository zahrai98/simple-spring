package com.example.sample.user.controller;

import com.example.sample.user.model.dto.ProfileIn;
import com.example.sample.user.model.dto.ProfileInEdit;
import com.example.sample.user.model.dto.ProfileInQuery;
import com.example.sample.user.model.dto.ProfileOut;
import com.example.sample.user.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileOut>> getAll(@ModelAttribute ProfileInQuery profileInQuery) {
        return new ResponseEntity<>(profileService.getAll(profileInQuery), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfileOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(profileService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileOut> create(@RequestBody @Valid ProfileIn profile) {
        return new ResponseEntity<>(profileService.create(profile), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ProfileOut> update(@PathVariable("id") Long id, @RequestBody @Valid ProfileInEdit profile) {
        return new ResponseEntity<>(profileService.update(id, profile), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long id) {
        profileService.delete(id);
    }
}

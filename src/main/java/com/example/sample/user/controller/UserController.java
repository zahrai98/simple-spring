package com.example.sample.user.controller;

import com.example.sample.user.model.dto.*;
import com.example.sample.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserOut>> getAll(@ModelAttribute UserFilter userFilter) {
        return new ResponseEntity<>(userService.getAll(userFilter), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserOut> create(@RequestBody @Valid UserIn user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserOut> update(@PathVariable("id") Long id, @RequestBody @Valid UserInEdit user) {
        return new ResponseEntity<>(userService.update(id, user), HttpStatus.OK);
    }

    @PutMapping(value = "/reset-pass/{id}")
    public ResponseEntity<UserOut> updatePassword(@PathVariable("id") Long id, @RequestBody @Valid UserInEditPassword user) {
        return new ResponseEntity<>(userService.updatePassword(id, user), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}

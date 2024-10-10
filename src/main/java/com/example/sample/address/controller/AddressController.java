package com.example.sample.address.controller;

import com.example.sample.address.model.dto.AddressInEdit;
import com.example.sample.address.model.dto.AddressIn;
import com.example.sample.address.model.dto.AddressInQuery;
import com.example.sample.address.model.dto.AddressOut;
import com.example.sample.address.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressOut>> findAll(@ModelAttribute AddressInQuery addressInQuery) {
        return new ResponseEntity<>(addressService.getAll(addressInQuery), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<AddressOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressOut> create(@Valid @RequestBody AddressIn address) {
        return new ResponseEntity<>(addressService.create(address), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        addressService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressOut> update(@PathVariable("id") Long id, @RequestBody @Valid AddressInEdit address) {
        return new ResponseEntity<>(addressService.update(id, address), HttpStatus.OK);
    }
}

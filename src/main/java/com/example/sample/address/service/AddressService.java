package com.example.sample.address.service;

import com.example.sample.address.model.dto.AddressInEdit;
import com.example.sample.address.model.dto.AddressIn;
import com.example.sample.address.model.dto.AddressInQuery;
import com.example.sample.address.model.dto.AddressOut;
import com.example.sample.address.model.AddressEntity;
import com.example.sample.address.repository.AddressRepository;
import com.example.sample.address.repository.AddressSpecification;
import com.example.sample.user.model.ProfileEntity;
import com.example.sample.user.repository.ProfileRepository;
import com.example.sample.user.repository.UserRepository;
import com.example.sample.config.errors.ErrorCodes;
import com.example.sample.config.exceptions.SystemException;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;

    public AddressService(AddressRepository addressRepository, ProfileRepository profileRepository) {
        this.addressRepository = addressRepository;
        this.profileRepository = profileRepository;
    }

    @Cacheable(value = "address")
    public List<AddressOut> getAll(AddressInQuery addressInQuery) {
        return addressRepository.findAll(
                Specification.where(AddressSpecification.search(addressInQuery))
        ).stream().map(AddressOut::new).toList();
    }

    @Cacheable(value = "address", key = "#id")
    public AddressOut getById(Long id) {
        return new AddressOut(addressRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "address not found", ErrorCodes.notFound)));
    }

    @Transactional(rollbackOn = Exception.class)
    public AddressOut create(AddressIn address) {
        ProfileEntity profile = profileRepository.findByUserId(address.getUserId()).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound));
        AddressEntity addressEntity = address.convertToEntity(null);
        addressEntity.setProfile(profile);
        return new AddressOut(addressRepository.save(addressEntity));

    }

    @Transactional(rollbackOn = Exception.class)
    @CachePut(value = "address", key = "#id")
    public AddressOut update(Long id, AddressInEdit addressInEdit) {
        AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "address with id " + id + "not found", ErrorCodes.notFound));
        return new AddressOut(addressRepository.save(addressInEdit.convertToEntity(addressEntity)));

    }

    @CacheEvict(value = "address", key = "#id")
    public void deleteById(Long id) {
        addressRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "address not found", ErrorCodes.notFound));
        addressRepository.deleteById(id);
    }
}

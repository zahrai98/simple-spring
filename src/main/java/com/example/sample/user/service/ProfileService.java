package com.example.sample.user.service;

import com.example.sample.user.model.ProfileEntity;
import com.example.sample.user.model.UserEntity;
import com.example.sample.user.model.dto.ProfileIn;
import com.example.sample.user.model.dto.ProfileInEdit;
import com.example.sample.user.model.dto.ProfileFilter;
import com.example.sample.user.model.dto.ProfileOut;
import com.example.sample.user.repository.ProfileRepository;
import com.example.sample.user.repository.ProfileSpecification;
import com.example.sample.user.repository.UserRepository;
import com.example.sample.config.errors.ErrorCodes;
import com.example.sample.config.exceptions.SystemException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public List<ProfileOut> getAll(ProfileFilter profileFilter) {
        return profileRepository.findAll(
                Specification.where(ProfileSpecification.search(profileFilter))
        ).stream().map(ProfileOut::new).toList();
    }

    public ProfileOut getById(Long id) {
        return new ProfileOut(profileRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "profile not found", ErrorCodes.notFound)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ProfileOut create(ProfileIn profileIn) {
        UserEntity user = userRepository.findById(profileIn.getUserId()).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound));
        ProfileEntity profileEntity = profileIn.convertToEntity(null);
        profileEntity.setUser(user);
        return new ProfileOut(profileRepository.save(profileEntity));
    }

    @Transactional(rollbackOn = Exception.class)
    public ProfileOut update(Long id, ProfileInEdit profileInEdit) {
        ProfileEntity profile = profileInEdit.convertToEntity(profileRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "profile not found", ErrorCodes.notFound)));
        return new ProfileOut(profileRepository.save(profile));
    }

    public void delete(Long id) {
        profileRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "profile not found", ErrorCodes.notFound));
        profileRepository.deleteById(id);
    }

}

package com.example.sample.user.service;

import com.example.sample.user.model.UserEntity;
import com.example.sample.user.model.dto.*;
import com.example.sample.user.repository.UserRepository;
import com.example.sample.user.repository.UserSpecification;
import com.example.sample.config.errors.ErrorCodes;
import com.example.sample.config.exceptions.SystemException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserOut> getAll(UserInQuery userInQuery) {
        return userRepository.findAll(
                Specification.where(UserSpecification.search(userInQuery))
        ).stream().map(UserOut::new).toList();
    }

    public UserOut getById(Long id) {
        return new UserOut(userRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound)));
    }

    public UserOut create(UserIn userIn) {
        if (userRepository.findByUsername(userIn.getUsername()).isPresent()) {
            throw new SystemException(HttpStatus.CONFLICT, "this username already exists", ErrorCodes.conflictData);
        }
        return new UserOut(userRepository.save(userIn.convertToEntity(null)));
    }

    public UserOut update(Long id, UserInEdit userInEdit) {
        if (userRepository.findByUsername(userInEdit.getUsername()).isPresent()) {
            throw new SystemException(HttpStatus.CONFLICT, "username already exists", ErrorCodes.conflictData);
        }
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound));
        return new UserOut(userRepository.save(userInEdit.convertToEntity(userEntity)));
    }

    public UserOut updatePassword(Long id, UserInEditPassword userInEdit) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound));
        return new UserOut(userRepository.save(userInEdit.convertToEntity(userEntity)));
    }

    public void delete(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound));
        userRepository.delete(user);
    }
}

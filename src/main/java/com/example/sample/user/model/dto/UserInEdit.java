package com.example.sample.user.model.dto;

import com.example.sample.user.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInEdit {
    @NotBlank
    private String username;

    public UserEntity convertToEntity(UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        userEntity.setUsername(username);
        return userEntity;
    }
}

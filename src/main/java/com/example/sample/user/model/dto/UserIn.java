package com.example.sample.user.model.dto;

import com.example.sample.user.utilities.PasswordManager;
import com.example.sample.user.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserIn {
    @NotBlank
    @NotBlank
    private String username;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    public UserEntity convertToEntity(UserEntity userEntity) {
        if (userEntity == null) {
            userEntity = new UserEntity();
        }
        userEntity.setUsername(this.username);
        userEntity.setPassword(PasswordManager.hashPassword(this.password));
        return userEntity;
    }
}

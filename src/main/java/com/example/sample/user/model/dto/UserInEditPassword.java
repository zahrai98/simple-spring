package com.example.sample.user.model.dto;

import com.example.sample.user.utilities.PasswordManager;
import com.example.sample.user.model.UserEntity;
import com.example.sample.config.errors.ErrorCodes;
import com.example.sample.config.exceptions.SystemException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInEditPassword {
    @NotNull
    @NotBlank
    private String oldPassword;
    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;

    public UserEntity convertToEntity(UserEntity userEntity) {
        if (userEntity != null) {
            if (!PasswordManager.checkPassword(this.oldPassword, userEntity.getPassword())) {
                throw new SystemException(HttpStatus.FORBIDDEN, "invalid old password", ErrorCodes.wrongPassword);
            }
            userEntity.setPassword(PasswordManager.hashPassword(this.newPassword));
        }
        return userEntity;
    }
}

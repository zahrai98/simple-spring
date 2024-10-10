package com.example.sample.user.model.dto;

import com.example.sample.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOut {
    private Long id;
    private String username;

    public UserOut(UserEntity userEntity) {
        if (userEntity != null) {
            this.id = userEntity.getId();
            this.username = userEntity.getUsername();
        }
    }
}

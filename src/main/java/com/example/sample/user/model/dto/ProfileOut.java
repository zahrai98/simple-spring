package com.example.sample.user.model.dto;

import com.example.sample.user.model.ProfileEntity;
import com.example.sample.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileOut {
    private Long id;
    private  String bio;
    private UserOut user;

    public ProfileOut(ProfileEntity profileEntity) {
        if (profileEntity != null) {
            this.id = profileEntity.getId();
            this.bio = profileEntity.getBio();
            this.user = new UserOut(profileEntity.getUser());
        }
    }


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserOut {
        private Long userId;
        private String username;

        public UserOut(UserEntity userEntity) {
            if (userEntity != null) {
                this.userId = userEntity.getId();
                this.username = userEntity.getUsername();
            }
        }
    }
}

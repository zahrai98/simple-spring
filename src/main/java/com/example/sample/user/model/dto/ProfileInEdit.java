package com.example.sample.user.model.dto;

import com.example.sample.user.model.ProfileEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInEdit {
    @NotNull
    private String bio;

    public ProfileEntity convertToEntity(ProfileEntity profileEntity) {
        if (profileEntity == null) {
            profileEntity = new ProfileEntity();
        }
        profileEntity.setBio(this.getBio());
        return profileEntity;
    }
}

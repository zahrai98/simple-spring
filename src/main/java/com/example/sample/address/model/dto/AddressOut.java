package com.example.sample.address.model.dto;

import com.example.sample.address.model.AddressEntity;
import com.example.sample.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressOut {
    private Long id;
    private String province;
    private String city;
    private String street;
    private String plate;
    private UserOut user;


    public AddressOut(AddressEntity address) {
        if (address != null) {
            this.id = address.getId();
            this.province = address.getProvince();
            this.city = address.getCity();
            this.street = address.getStreet();
            this.plate = address.getPlate();
            this.user = new UserOut(address.getProfile().getUser());
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserOut{
        private Long id;
        private String username;

        public UserOut(UserEntity user) {
            if (user != null) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

    }
}


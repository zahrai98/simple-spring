package com.example.sample.address.model.dto;

import com.example.sample.address.model.AddressEntity;
import com.example.sample.address.validations.ValidStreet;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AddressInEdit {
    @NotBlank
    private String province;
    @NotBlank
    private String city;
    //    @Size(min = 4)
    @ValidStreet
    private String street;
    private String plate;

    public AddressEntity convertToEntity(AddressEntity addressEntity) {
        if (addressEntity == null) {
            addressEntity = new AddressEntity();
        }

        addressEntity.setProvince(this.province);
        addressEntity.setCity(this.city);
        addressEntity.setStreet(this.street);
        addressEntity.setPlate(this.plate);

        return addressEntity;
    }
}

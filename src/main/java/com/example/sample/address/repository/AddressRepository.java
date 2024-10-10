package com.example.sample.address.repository;

import com.example.sample.address.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>, JpaSpecificationExecutor<AddressEntity> {
    Optional<AddressEntity> findByProvinceAndCityAndStreetAndPlate(String province, String city, String street, String plate);
}

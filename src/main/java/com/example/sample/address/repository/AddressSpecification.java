package com.example.sample.address.repository;

import com.example.sample.address.model.AddressEntity;
import com.example.sample.address.model.dto.AddressInQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class AddressSpecification {

    public static Specification<AddressEntity> search(AddressInQuery addressInQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (addressInQuery.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), addressInQuery.getId()));
            }
            if (addressInQuery.getProvince() != null) {
                predicates.add(criteriaBuilder.equal(root.get("province"), addressInQuery.getProvince()));
            }
            if (addressInQuery.getCity() != null) {
                predicates.add(criteriaBuilder.equal(root.get("city"), addressInQuery.getCity()));
            }
            if (addressInQuery.getStreet() != null) {
                predicates.add(criteriaBuilder.like(root.get("street"), "%" + addressInQuery.getStreet() + "%"));
            }
            if (addressInQuery.getPlate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("plate"), addressInQuery.getPlate()));
            }
            if (addressInQuery.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("profile").get("user").get("username"), addressInQuery.getUsername()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

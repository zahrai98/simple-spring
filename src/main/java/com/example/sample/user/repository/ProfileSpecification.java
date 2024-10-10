package com.example.sample.user.repository;

import com.example.sample.user.model.ProfileEntity;
import com.example.sample.user.model.dto.ProfileInQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProfileSpecification {
    public static Specification<ProfileEntity> search(ProfileInQuery profileInQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (profileInQuery.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), profileInQuery.getId()));
            }
            if (profileInQuery.getBio() != null) {
                predicates.add(criteriaBuilder.equal(root.get("bio"), "%" + profileInQuery.getBio() + "%"));
            }
            if (profileInQuery.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("username"), profileInQuery.getUsername()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
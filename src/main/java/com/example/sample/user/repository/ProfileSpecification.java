package com.example.sample.user.repository;

import com.example.sample.user.model.ProfileEntity;
import com.example.sample.user.model.dto.ProfileFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProfileSpecification {
    public static Specification<ProfileEntity> search(ProfileFilter profileFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (profileFilter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), profileFilter.getId()));
            }
            if (profileFilter.getBio() != null) {
                predicates.add(criteriaBuilder.equal(root.get("bio"), "%" + profileFilter.getBio() + "%"));
            }
            if (profileFilter.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("username"), profileFilter.getUsername()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
package com.example.sample.user.repository;

import com.example.sample.user.model.UserEntity;
import com.example.sample.user.model.dto.UserFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> search(UserFilter userFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userFilter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), userFilter.getId()));
            }
            if (userFilter.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("username"), userFilter.getUsername()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
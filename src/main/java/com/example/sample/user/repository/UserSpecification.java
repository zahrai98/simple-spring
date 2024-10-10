package com.example.sample.user.repository;

import com.example.sample.user.model.UserEntity;
import com.example.sample.user.model.dto.UserInQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<UserEntity> search(UserInQuery userInQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userInQuery.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), userInQuery.getId()));
            }
            if (userInQuery.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("username"), userInQuery.getUsername()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
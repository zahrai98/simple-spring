package com.example.sample.todo.repository;

import com.example.sample.todo.model.TodoEntity;
import com.example.sample.todo.model.dto.TodoFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TodoSpecifications {
    public static Specification<TodoEntity> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("isDeleted"));
    }

    public static Specification<TodoEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<TodoEntity> search(TodoFilter todoFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (todoFilter.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), todoFilter.getId()));
            }
            if (todoFilter.getTitle() != null) {
                predicates.add(criteriaBuilder.equal(root.get("title"), todoFilter.getTitle()));
            }
            if (todoFilter.getDescription() != null) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + todoFilter.getDescription() + "%"));
            }
            if (todoFilter.getStartDueDate() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("dueDate"), todoFilter.getStartDueDate()));
            }
            if (todoFilter.getEndDueDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), todoFilter.getEndDueDate()));
            }
            if (todoFilter.getStartCreateDate() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("createdAt"), todoFilter.getStartCreateDate()));
            }
            if (todoFilter.getEndCreateDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), todoFilter.getEndCreateDate()));
            }
            if (todoFilter.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("username"), todoFilter.getUsername()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

package com.example.sample.todo.repository;

import com.example.sample.todo.model.TodoEntity;
import com.example.sample.todo.model.dto.TodoInQuery;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoSpecifications {
    public static Specification<TodoEntity> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("isDeleted"));
    }

    public static Specification<TodoEntity> hasId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<TodoEntity> search(TodoInQuery todoInQuery) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (todoInQuery.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), todoInQuery.getId()));
            }
            if (todoInQuery.getTitle() != null) {
                predicates.add(criteriaBuilder.equal(root.get("title"), todoInQuery.getTitle()));
            }
            if (todoInQuery.getDescription() != null) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + todoInQuery.getDescription() + "%"));
            }
            if (todoInQuery.getStartDueDate() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("dueDate"), todoInQuery.getStartDueDate()));
            }
            if (todoInQuery.getEndDueDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), todoInQuery.getEndDueDate()));
            }
            if (todoInQuery.getStartCreateDate() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("createdAt"), todoInQuery.getStartCreateDate()));
            }
            if (todoInQuery.getEndCreateDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), todoInQuery.getEndCreateDate()));
            }
            if (todoInQuery.getUsername() != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("username"), todoInQuery.getUsername()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

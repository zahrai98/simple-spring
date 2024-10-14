package com.example.sample.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoFilter {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDueDate;
    private LocalDateTime endDueDate;
    private LocalDateTime startCreateDate;
    private LocalDateTime endCreateDate;
    private String username;
}

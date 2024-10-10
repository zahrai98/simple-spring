package com.example.sample.todo.model.dto;

import com.example.sample.todo.model.TodoEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoInEdit {
    @NotNull
    private String title;
    private String description;
    @FutureOrPresent
    private LocalDateTime dueDate;

    public TodoEntity convertToEntity(TodoEntity todoEntity) {
        if (todoEntity != null) {
            todoEntity.setTitle(this.title);
            todoEntity.setDescription(this.description);
            todoEntity.setDueDate(this.dueDate);
        }
        return todoEntity;
    }
}

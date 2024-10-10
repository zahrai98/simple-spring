package com.example.sample.todo.model.dto;

import com.example.sample.todo.model.TodoEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoIn {
    @NotNull
    private String title;
    private String description;
    @FutureOrPresent
    private LocalDateTime dueDate;
    @NotNull
    private Long userId;

    public TodoEntity convertToEntity(TodoEntity todoEntity){
        if (todoEntity == null){
            todoEntity = new TodoEntity();
        }
        todoEntity.setTitle(this.title);
        todoEntity.setDescription(this.description);
        todoEntity.setDueDate(this.dueDate);
        todoEntity.setIsDeleted(false);
        todoEntity.setCreateDate(LocalDateTime.now());
        return todoEntity;
    }
}

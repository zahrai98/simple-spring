package com.example.sample.todo.model.dto;

import com.example.sample.todo.model.TodoEntity;
import com.example.sample.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoOut {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime createDate;
    private UserOut user;

    public TodoOut(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.description = todoEntity.getDescription();
        this.dueDate = todoEntity.getDueDate();
        this.createDate = todoEntity.getCreateDate();
        this.user = new UserOut(todoEntity.getUser());
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserOut {
        private Long userId;
        private String username;

        public UserOut(UserEntity userEntity) {
            this.userId = userEntity.getId();
            this.username = userEntity.getUsername();
        }
    }


}

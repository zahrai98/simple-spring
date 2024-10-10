package com.example.sample.todo.service;

import com.example.sample.todo.model.TodoEntity;
import com.example.sample.todo.model.dto.TodoIn;
import com.example.sample.todo.model.dto.TodoInEdit;
import com.example.sample.todo.model.dto.TodoInQuery;
import com.example.sample.todo.model.dto.TodoOut;
import com.example.sample.todo.repository.TodoRepository;
import com.example.sample.todo.repository.TodoSpecifications;
import com.example.sample.user.model.UserEntity;
import com.example.sample.user.repository.UserRepository;
import com.example.sample.config.errors.ErrorCodes;
import com.example.sample.config.exceptions.SystemException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<TodoOut> getAll(TodoInQuery todoInQuery) {
        return todoRepository.findAll(Specification.where(
                TodoSpecifications.search(todoInQuery))).stream().map(TodoOut::new).toList();
    }

    public TodoOut getById(Long id) {
        return new TodoOut(todoRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "todo with id: " + id + " not found", ErrorCodes.notFound)));
    }

    public TodoOut create(TodoIn todoIn) {
        UserEntity user = userRepository.findById(todoIn.getUserId()).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "user not found", ErrorCodes.notFound));
        TodoEntity todoEntity = todoIn.convertToEntity(null);
        todoEntity.setUser(user);
        return new TodoOut(todoRepository.save(todoEntity));
    }

    public TodoOut update(Long id, TodoInEdit todoInEdit) {
        TodoEntity todo = todoRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "todo not found", ErrorCodes.notFound));
        return new TodoOut(todoRepository.save(todoInEdit.convertToEntity(todo)));
    }

    public void delete(Long id) {
        todoRepository.findById(id).orElseThrow(() ->
                new SystemException(HttpStatus.NOT_FOUND, "todo not found", ErrorCodes.notFound));
        todoRepository.deleteById(id);
    }
}

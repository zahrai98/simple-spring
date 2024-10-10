package com.example.sample.todo.controller;

import com.example.sample.todo.model.dto.TodoIn;
import com.example.sample.todo.model.dto.TodoInEdit;
import com.example.sample.todo.model.dto.TodoInQuery;
import com.example.sample.todo.model.dto.TodoOut;
import com.example.sample.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoOut>> getAll(@ModelAttribute TodoInQuery todoInQuery) {
        return new ResponseEntity<>(todoService.getAll(todoInQuery), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoOut> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(todoService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoOut> create(@RequestBody @Valid TodoIn todoIn) {
        return new ResponseEntity<>(todoService.create(todoIn), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TodoOut> update(@PathVariable("id") Long id, @RequestBody @Valid TodoInEdit todoInEdit) {
        return new ResponseEntity<>(todoService.update(id, todoInEdit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        todoService.delete(id);
    }
}

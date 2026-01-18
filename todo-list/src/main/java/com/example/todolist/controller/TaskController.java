package com.example.todolist.controller;

import com.example.todolist.domain.dto.request.TodoRequest;
import com.example.todolist.domain.dto.response.TodoResponse;
import com.example.todolist.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TaskController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        TodoResponse response = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        List<TodoResponse> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        TodoResponse response = todoService.getTodoById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request) {
        TodoResponse response = todoService.updateTodo(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoResponse> completeTodo(@PathVariable Long id) {
        TodoResponse response = todoService.completeTodo(id);
        return ResponseEntity.ok(response);
    }
}
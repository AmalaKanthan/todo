package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.service.AuthService;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.getTodoById(id);
        return ResponseEntity.ok(todoDTO);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodoForUser(@RequestParam Long userId, @RequestBody TodoDTO todoDTO) {
        TodoDTO createdTodoDTO = todoService.createTodoForUser(userId, todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO updatedTodoDTO) {
        TodoDTO todoDTO = todoService.updateTodo(id, updatedTodoDTO);
        return ResponseEntity.ok(todoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}

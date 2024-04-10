package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoItemDTO;
import com.example.todoapp.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo-items")
public class TodoItemController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public ResponseEntity<TodoItemDTO> createTodoItem(@RequestParam Long todoId, @RequestBody TodoItemDTO todoItemDTO) {
        TodoItemDTO createdTodoItemDTO = todoItemService.createTodoItem(todoId, todoItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodoItemDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItemDTO> updateTodoItem(@PathVariable Long id, @RequestBody TodoItemDTO updatedTodoItemDTO) {
        TodoItemDTO updatedTodoItem = todoItemService.updateTodoItem(id, updatedTodoItemDTO);
        return ResponseEntity.ok(updatedTodoItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        todoItemService.deleteTodoItem(id);
        return ResponseEntity.noContent().build();
    }
}

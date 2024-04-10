package com.example.todoapp.service;

import com.example.todoapp.dto.TodoItemDTO;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.entity.TodoItem;
import com.example.todoapp.exception.TodoItemNotFoundException;
import com.example.todoapp.exception.TodoNotFoundException;
import com.example.todoapp.repository.TodoItemRepository;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository, TodoRepository todoRepository) {
        this.todoItemRepository = todoItemRepository;
        this.todoRepository = todoRepository;
    }

    public TodoItemDTO createTodoItem(Long todoId, TodoItemDTO todoItemDTO) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + todoId));

        TodoItem todoItem = todoItemDTO.mapToEntity(todoItemDTO);
        todo.getTodoItems().add(todoItem);
        todoRepository.save(todo);

        return TodoItemDTO.mapToDTO(todoItemRepository.save(todoItem));
    }

    public TodoItemDTO updateTodoItem(Long todoItemId, TodoItemDTO updatedTodoItemDTO) {
        TodoItem todoItem = todoItemRepository.findById(todoItemId)
                .orElseThrow(() -> new TodoItemNotFoundException("TodoItem not found with id: " + todoItemId));

        updatedTodoItemDTO.mapToEntity(updatedTodoItemDTO);

        return TodoItemDTO.mapToDTO(todoItemRepository.save(todoItem));
    }

    public void deleteTodoItem(Long todoItemId) {
        TodoItem todoItem = todoItemRepository.findById(todoItemId)
                .orElseThrow(() -> new TodoItemNotFoundException("TodoItem not found with id: " + todoItemId));

        todoItemRepository.delete(todoItem);
    }
}

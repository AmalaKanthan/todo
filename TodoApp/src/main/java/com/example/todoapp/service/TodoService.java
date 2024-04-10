package com.example.todoapp.service;

import com.example.todoapp.dto.TodoDTO;
import com.example.todoapp.dto.UserDTO;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.entity.User;
import com.example.todoapp.exception.TodoNotFoundException;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    @Autowired
    public TodoService(TodoRepository todoRepository,UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    public TodoDTO getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
        return TodoDTO.mapToDTO(todo);
    }

    public TodoDTO createTodoForUser(Long userId, TodoDTO todoDTO) {
        User user = userService.getUserById(userId).mapToEntity();
        todoDTO.setUser(UserDTO.mapToDto(user));
        Todo todo = todoDTO.mapToEntity(todoDTO);
        todo = todoRepository.save(todo);
        return TodoDTO.mapToDTO(todo);
    }

    public TodoDTO updateTodo(Long id, TodoDTO updatedTodoDTO) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
        updatedTodoDTO.setId(id);
        todo = updatedTodoDTO.mapToEntity(updatedTodoDTO);
        todo = todoRepository.save(todo);
        return TodoDTO.mapToDTO(todo);
    }

    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }

    public List<TodoDTO> getAllTodoListsByUserId(Long userId) {
        List<Todo> todos = todoRepository.findAllByUserId(userId);
        return todos.stream()
                .map(TodoDTO::mapToDTO)
                .collect(Collectors.toList());
    }
}

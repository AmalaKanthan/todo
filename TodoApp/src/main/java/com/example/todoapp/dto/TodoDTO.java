package com.example.todoapp.dto;

import com.example.todoapp.entity.Todo;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDTO {

    private Long id;
    private List<TodoItemDTO> todos;

    @NotNull(message = "User is required")
    private UserDTO user;
    private Date createdAt;
    private Date updatedAt;

    public static Todo mapToEntity(TodoDTO todoDTO) {
        Todo todo = new Todo();
        todo.setId(todoDTO.getId());
        todo.setUser(todoDTO.getUser().mapToEntity());
        todo.setTodoItems(todoDTO.getTodos().stream().map(TodoItemDTO::mapToEntity).collect(Collectors.toList()));
        todo.setCreatedAt(todoDTO.getCreatedAt());
        todo.setUpdatedAt(todoDTO.getUpdatedAt());
        return todo;
    }

    public static TodoDTO mapToDTO(Todo todo) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(todo.getId());
        todoDTO.setUser(UserDTO.mapToDto(todo.getUser()));
        todoDTO.setTodos(todo.getTodoItems().stream().map(TodoItemDTO::mapToDTO).collect(Collectors.toList()));
        todoDTO.setCreatedAt(todo.getCreatedAt());
        todoDTO.setUpdatedAt(todo.getUpdatedAt());
        return todoDTO;
    }
}

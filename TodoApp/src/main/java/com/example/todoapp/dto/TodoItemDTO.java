package com.example.todoapp.dto;

import com.example.todoapp.entity.TodoItem;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoItemDTO {
    private Long id;

    @NotNull(message = "Task is required")
    private String task;

    public static TodoItem mapToEntity(TodoItemDTO todoItemDTO) {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(todoItemDTO.getId());
        todoItem.setTask(todoItemDTO.getTask());
        return todoItem;
    }

    public static TodoItemDTO mapToDTO(TodoItem todoItem) {
        TodoItemDTO todoItemDTO = new TodoItemDTO();
        todoItemDTO.setId(todoItem.getId());
        todoItemDTO.setTask(todoItem.getTask());
        return todoItemDTO;
    }
}

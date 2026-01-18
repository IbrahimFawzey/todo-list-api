package com.example.todolist.domain.dto.request;

import com.example.todolist.domain.enums.TodoPriority;
import com.example.todolist.domain.enums.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Status is required")
    private TodoStatus status;

    @NotNull(message = "Priority is required")
    private TodoPriority priority;

    private LocalDateTime dueDate;
}
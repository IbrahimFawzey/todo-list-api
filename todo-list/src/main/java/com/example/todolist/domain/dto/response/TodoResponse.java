package com.example.todolist.domain.dto.response;

import com.example.todolist.domain.enums.TodoPriority;
import com.example.todolist.domain.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {

    private Long id;
    private String title;
    private String description;
    private TodoStatus status;
    private TodoPriority priority;
    private LocalDateTime dueDate;
    private LocalDateTime completedAt;
    private Boolean isDeleted;
}
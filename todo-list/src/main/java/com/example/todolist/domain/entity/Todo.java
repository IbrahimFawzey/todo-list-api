package com.example.todolist.domain.entity;

import com.example.todolist.domain.enums.TodoPriority;
import com.example.todolist.domain.enums.TodoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "todo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoPriority priority;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;


    }


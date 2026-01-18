package com.example.todolist.service;

import com.example.todolist.domain.dto.request.TodoRequest;
import com.example.todolist.domain.dto.response.TodoResponse;
import com.example.todolist.domain.entity.Todo;
import com.example.todolist.domain.enums.TodoStatus;
import com.example.todolist.exception.BusinessException;
import com.example.todolist.exception.ErrorCodes;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponse createTodo(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setStatus(request.getStatus());
        todo.setPriority(request.getPriority());
        todo.setDueDate(request.getDueDate());
        todo.setIsDeleted(false);

        Todo savedTodo = todoRepository.save(todo);

        return convertToResponse(savedTodo);
    }

    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .filter(todo -> !todo.getIsDeleted())
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCodes.TODO_NOT_FOUND,
                        "Todo not found with id: " + id
                ));

        if (todo.getIsDeleted()) {
            throw new BusinessException(
                    ErrorCodes.TODO_ALREADY_DELETED,
                    "Todo has been deleted"
            );
        }

        return convertToResponse(todo);
    }

    @Transactional
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCodes.TODO_NOT_FOUND,
                        "Todo not found with id: " + id
                ));

        if (todo.getIsDeleted()) {
            throw new BusinessException(
                    ErrorCodes.TODO_CANNOT_UPDATE_DELETED,
                    "Cannot update deleted todo"
            );
        }

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setStatus(request.getStatus());
        todo.setPriority(request.getPriority());
        todo.setDueDate(request.getDueDate());

        Todo updatedTodo = todoRepository.save(todo);

        return convertToResponse(updatedTodo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCodes.TODO_NOT_FOUND,
                        "Todo not found with id: " + id
                ));

        if (todo.getIsDeleted()) {
            throw new BusinessException(
                    ErrorCodes.TODO_ALREADY_DELETED,
                    "Todo is already deleted"
            );
        }

        todo.setIsDeleted(true);
        todoRepository.save(todo);
    }

    @Transactional
    public TodoResponse completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCodes.TODO_NOT_FOUND,
                        "Todo not found with id: " + id
                ));

        if (todo.getIsDeleted()) {
            throw new BusinessException(
                    ErrorCodes.TODO_CANNOT_COMPLETE_DELETED,
                    "Cannot complete deleted todo"
            );
        }

        if (todo.getStatus() == TodoStatus.COMPLETED) {
            throw new BusinessException(
                    ErrorCodes.TODO_ALREADY_COMPLETED,
                    "Todo is already completed"
            );
        }

        todo.setStatus(TodoStatus.COMPLETED);
        todo.setCompletedAt(LocalDateTime.now());

        Todo completedTodo = todoRepository.save(todo);

        return convertToResponse(completedTodo);
    }

    private TodoResponse convertToResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setStatus(todo.getStatus());
        response.setPriority(todo.getPriority());
        response.setDueDate(todo.getDueDate());
        response.setCompletedAt(todo.getCompletedAt());
        response.setIsDeleted(todo.getIsDeleted());
        return response;
    }
}
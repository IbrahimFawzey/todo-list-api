package com.example.todolist.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private final String errorCode;
    private final String message;

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
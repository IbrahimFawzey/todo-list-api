package com.example.todolist.exception;

public class ErrorCodes {

    public static final String TODO_NOT_FOUND = "TODO_001";
    public static final String TODO_ALREADY_DELETED = "TODO_002";
    public static final String TODO_ALREADY_COMPLETED = "TODO_003";
    public static final String TODO_INVALID_STATUS = "TODO_004";
    public static final String TODO_INVALID_PRIORITY = "TODO_005";
    public static final String TODO_CANNOT_UPDATE_DELETED = "TODO_006";
    public static final String TODO_CANNOT_COMPLETE_DELETED = "TODO_007";

    private ErrorCodes() {
    }
}
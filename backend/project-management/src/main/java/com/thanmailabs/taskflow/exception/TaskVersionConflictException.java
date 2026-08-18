package com.thanmailabs.taskflow.exception;

public class TaskVersionConflictException extends RuntimeException {
    public TaskVersionConflictException(String message) {
        super(message);
    }
}

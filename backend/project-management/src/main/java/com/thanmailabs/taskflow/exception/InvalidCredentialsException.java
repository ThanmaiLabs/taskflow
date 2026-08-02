package com.thanmailabs.taskflow.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}

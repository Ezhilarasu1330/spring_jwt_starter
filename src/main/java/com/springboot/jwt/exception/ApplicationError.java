package com.springboot.jwt.exception;

public class ApplicationError extends Exception {

    public ApplicationError(final String message) {
        super(message);
    }

}

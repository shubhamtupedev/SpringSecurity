package com.example.springsecurity.exceptions;

public class ApplicationServiceException extends Exception {
    public ApplicationServiceException() {
        super("Something went wrong. Please contact administrator!");
    }
}

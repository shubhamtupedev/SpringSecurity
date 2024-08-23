package com.example.springsecurity.Exception;

public class ServiceException extends Exception {
    public ServiceException() {
        super("Something Went Wrong! please contact Administrator");
    }
}

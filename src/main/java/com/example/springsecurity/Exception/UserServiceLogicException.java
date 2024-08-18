package com.example.springsecurity.Exception;

public class UserServiceLogicException extends Exception {

    public UserServiceLogicException() {
        super("Something Went Wrong! please contact Administrator");
    }
}

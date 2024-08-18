package com.example.springsecurity.handler;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserNotFoundException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class UserServiceExceptionHandler {


    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<?>> userAlreadyExistsExceptionHandler(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), userAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> userNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), userNotFoundException.getMessage()));
    }

    @ExceptionHandler(value = UserServiceLogicException.class)
    public ResponseEntity<ApiResponseDto<?>> userServiceLogicException(UserServiceLogicException userServiceLogicException) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), userServiceLogicException.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorMessage = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), errorMessage.toString()));
    }
}

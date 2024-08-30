package com.example.springsecurity.handler;

import com.example.springsecurity.Exception.*;
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
public class ExceptionsHandler {

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ApiResponseDto<?>> validationException(ValidationException validationException) {
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), validationException.getMessage()));
    }

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ApiResponseDto<?>> serviceException(ServiceException serviceException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), serviceException.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errorMessage = new ArrayList<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        System.out.println(errorMessage.toString());
        return ResponseEntity.badRequest().body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), errorMessage.toString()));
    }
}

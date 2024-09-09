package com.example.springsecurity.exceptions.handler;

import com.example.springsecurity.applicationenum.ApiApplicationStatus;
import com.example.springsecurity.exceptions.ApplicationServiceException;
import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.responseDTO.ApiResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = ApplicationServiceException.class)
    public ResponseEntity<ApiResponseDTO<?>> applicationServiceException(ApplicationServiceException exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ApiResponseDTO<?>> validationException(ValidationException exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<?>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMsg = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            errorMsg.add(error.getDefaultMessage());
        });

        if (errorMsg == null && errorMsg.isEmpty()) {
            errorMsg.add("Bad Request");
        }

        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), errorMsg.toString()));
    }
}

package com.example.springsecurity.services;

import com.example.springsecurity.Exception.*;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entityDTO.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<ApiResponseDto<?>> saveUser(UserDTO userDTO) throws ValidationException, ServiceException;

    public ResponseEntity<ApiResponseDto<?>> getUser(String email, int page, int size, String sortBy, String sortOrder) throws ValidationException, ServiceException;

    public ResponseEntity<ApiResponseDto<?>> deleteUser(String username) throws ValidationException, ServiceException;

    public ResponseEntity<ApiResponseDto<?>> updateUser(String username, User user) throws ValidationException, ServiceException;

    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws ValidationException, ServiceException;

}

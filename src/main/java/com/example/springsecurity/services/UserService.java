package com.example.springsecurity.services;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserNotFoundException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entityDTO.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<ApiResponseDto<?>> saveUser(UserDTO userDTO) throws UserAlreadyExistsException, UserServiceLogicException;

    public ResponseEntity<ApiResponseDto<?>> getUser(String username) throws UserNotFoundException, UserServiceLogicException;

    public ResponseEntity<ApiResponseDto<?>> deleteUser(String username) throws UserNotFoundException, UserServiceLogicException;

    public ResponseEntity<ApiResponseDto<?>> updateUser(String username, User user) throws UserNotFoundException, UserServiceLogicException;

    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException;

}

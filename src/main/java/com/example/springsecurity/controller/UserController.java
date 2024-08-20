package com.example.springsecurity.controller;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserNotFoundException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<ApiResponseDto<?>> getUser(@PathVariable String username) throws UserNotFoundException, UserServiceLogicException {
        return userService.getUser(username);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid @RequestBody User user) throws UserAlreadyExistsException, UserServiceLogicException {
        return userService.saveUser(user);
    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable String username) throws UserNotFoundException, UserServiceLogicException {
        return userService.deleteUser(username);
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @PathVariable String username, @RequestBody User user) throws UserNotFoundException, UserServiceLogicException {
        return userService.updateUser(username, user);
    }
}

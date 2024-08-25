package com.example.springsecurity.controller;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserNotFoundException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entityDTO.UserDTO;
import com.example.springsecurity.jwt.JwtUtils;
import com.example.springsecurity.jwt.LoginRequest;
import com.example.springsecurity.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtil;

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
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid @RequestBody UserDTO userDTO) throws UserAlreadyExistsException, UserServiceLogicException {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable String username) throws UserNotFoundException, UserServiceLogicException {
        return userService.deleteUser(username);
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @PathVariable String username, @RequestBody User user) throws UserNotFoundException, UserServiceLogicException {
        return userService.updateUser(username, user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), passwordEncoder.encode(loginRequest.getPassword())));
            String token = jwtUtil.generateTokenFromUsername(loginRequest.getUsername());
            response.put("token", token);
        } catch (AuthenticationException e) {
            response.put("error", "Invalid username or password");
        }
        return response;
    }
}

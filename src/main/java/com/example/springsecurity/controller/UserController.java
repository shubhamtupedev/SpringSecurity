package com.example.springsecurity.controller;

import com.example.springsecurity.Exception.*;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.Utility.EntityMapper;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entityDTO.UserDTO;
import com.example.springsecurity.jwt.JwtUtils;
import com.example.springsecurity.jwt.LoginRequest;
import com.example.springsecurity.jwt.LoginResponse;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private @Lazy UserRepository userRepository;


    @GetMapping("/user-profiles")
    public ResponseEntity<ApiResponseDto<?>> getUser(@RequestParam(required = false, name = "email") String email, @RequestParam(defaultValue = "0", name = "page") int page, @RequestParam(defaultValue = "10", name = "size") int size, @RequestParam(defaultValue = "email", name = "sortBy") String sortBy, @RequestParam(defaultValue = "asc", name = "sortOrder") String sortOrder) throws ValidationException, ServiceException {
        return userService.getUser(email, page, size, sortBy, sortOrder);
    }

    @GetMapping("/user-profile/{email}")
    public ResponseEntity<ApiResponseDto<?>> getUser(@PathVariable String email) throws ValidationException, ServiceException {
        return userService.getUser(email);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid @RequestBody UserDTO userDTO) throws ValidationException, ServiceException {
        return userService.saveUser(userDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponseDto<?>> login(@RequestBody LoginRequest loginRequest) {
        try {
            String decodedPassword = new String(Base64.getDecoder().decode(loginRequest.getPassword()));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), decodedPassword));
            String token = jwtUtil.generateTokenFromUsername(loginRequest.getEmail());
            LoginResponse response = new LoginResponse();
            Optional<User> userList = userRepository.findByEmail(loginRequest.getEmail());
            UserDTO userDTO = EntityMapper.mapToDto(userList.get(),UserDTO.class);
            response.setJwtToken(token);
            response.setUserDTO(userDTO);
            response.setMessage("User login successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), response));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto<>(ApiResponseStatus.FAIL.name(), e.getMessage()));
        }
    }

    @DeleteMapping("/users/{username}") 
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable String username) throws ValidationException, ServiceException {
        return userService.deleteUser(username);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @PathVariable String username, @RequestBody User user) throws ValidationException, ServiceException {
        return userService.updateUser(username, user);
    }


//    @PostMapping("/auth/logout")
//    public Map<String, String> logout(@RequestBody LoginRequest loginRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//            String token = jwtUtil.generateTokenFromUsername(loginRequest.getUsername());
//            response.put("token", token);
//        } catch (AuthenticationException e) {
//            response.put("error", "Invalid username or password");
//        }
//        return response;
//    }
//
//    @PostMapping("/auth/forgot-password")
//    public Map<String, String> forgotPassword(@RequestBody LoginRequest loginRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//            String token = jwtUtil.generateTokenFromUsername(loginRequest.getUsername());
//            response.put("token", token);
//        } catch (AuthenticationException e) {
//            response.put("error", "Invalid username or password");
//        }
//        return response;
//    }
//
//    @PostMapping("/auth/change-password")
//    public Map<String, String> changePassword(@RequestBody LoginRequest loginRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//            String token = jwtUtil.generateTokenFromUsername(loginRequest.getUsername());
//            response.put("token", token);
//        } catch (AuthenticationException e) {
//            response.put("error", "Invalid username or password");
//        }
//        return response;
//    }
//
//    @PostMapping("/auth/verify-email")
//    public Map<String, String> verifyEmail(@RequestBody LoginRequest loginRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//            String token = jwtUtil.generateTokenFromUsername(loginRequest.getUsername());
//            response.put("token", token);
//        } catch (AuthenticationException e) {
//            response.put("error", "Invalid username or password");
//        }
//        return response;
//    }
//
//    @PostMapping("/auth/resend-verification-email")
//    public Map<String, String> resendVerificationMail(@RequestBody LoginRequest loginRequest) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//            String token = jwtUtil.generateTokenFromUsername(loginRequest.getUsername());
//            response.put("token", token);
//        } catch (AuthenticationException e) {
//            response.put("error", "Invalid username or password");
//        }
//        return response;
//    }
}

package com.example.springsecurity.controller;

import com.example.springsecurity.apiDTO.LoginRequestDTO;
import com.example.springsecurity.applicationenum.ApiApplicationStatus;
import com.example.springsecurity.entityDTO.ApplicationUsersDTO;
import com.example.springsecurity.exceptions.ApplicationServiceException;
import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.responseDTO.ApiResponseDTO;
import com.example.springsecurity.service.ApplicationUsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
public class ApplicationUsersController {

    private ApplicationUsersService applicationUsersService;

    public ApplicationUsersController(ApplicationUsersService applicationUsersService) {
        this.applicationUsersService = applicationUsersService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponseDTO<?>> saveUser(@RequestBody @Valid ApplicationUsersDTO applicationUsersDTO) throws ApplicationServiceException, ValidationException {
        return applicationUsersService.saveUser(applicationUsersDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponseDTO<?>> loginUser(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws ApplicationServiceException, ValidationException {
        try {
            String decodedPassword = new String(Base64.getDecoder().decode(loginRequestDTO.getPassword()));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), decodedPassword));
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(ApiApplicationStatus.SUCCESS.name(), "User logged in successfully."));
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), exception.getMessage()));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), exception.getMessage()));
        }
    }

}

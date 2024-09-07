package com.example.springsecurity.controller;

import com.example.springsecurity.entity.ApplicationUsers;
import com.example.springsecurity.entityDTO.ApplicationUsersDTO;
import com.example.springsecurity.exceptions.ApplicationServiceException;
import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.responseDTO.ApiResponseDTO;
import com.example.springsecurity.service.ApplicationUsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApplicationUsersController {

    private ApplicationUsersService applicationUsersService;

    public ApplicationUsersController(ApplicationUsersService applicationUsersService) {
        this.applicationUsersService = applicationUsersService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponseDTO<?>> saveUser(@RequestBody ApplicationUsersDTO applicationUsersDTO) throws ApplicationServiceException, ValidationException {
        return applicationUsersService.saveUser(applicationUsersDTO);
    }
}

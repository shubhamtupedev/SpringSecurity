package com.example.springsecurity.service;

import com.example.springsecurity.entity.ApplicationUsers;
import com.example.springsecurity.entityDTO.ApplicationUsersDTO;
import com.example.springsecurity.exceptions.ApplicationServiceException;
import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.responseDTO.ApiResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationUsersService {
    public ResponseEntity<ApiResponseDTO<?>> saveUser(ApplicationUsersDTO applicationUsersDTO) throws ValidationException, ApplicationServiceException;

    public List<ApplicationUsers> getUserDetails() throws ApplicationServiceException;

}

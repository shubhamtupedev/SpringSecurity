package com.example.springsecurity.controller;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.entityDTO.SystemParameterDTO;
import com.example.springsecurity.repository.SystemParameterRepository;
import com.example.springsecurity.services.SystemPrameterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/systemparameter")
public class SystemParameterController {

    @Autowired
    private SystemPrameterService systemPrameterService;

    @PostMapping("/saveParameterDetails")
    public ResponseEntity<ApiResponseDto<?>> save(@Valid @RequestBody SystemParameterDTO systemParameterDTO) throws ValidationException, ServiceException {
        return systemPrameterService.saveSystemParameterDetails(systemParameterDTO);
    }

}

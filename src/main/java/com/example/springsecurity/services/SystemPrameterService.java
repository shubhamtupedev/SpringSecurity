package com.example.springsecurity.services;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.entityDTO.SystemParameterDTO;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface SystemPrameterService {

    public ResponseEntity<ApiResponseDto<?>> saveSystemParameterDetails(SystemParameterDTO systemParameterDTO) throws ValidationException, ServiceException;

    public ResponseEntity<ApiResponseDto<?>> updateSystemParameterDetails(Long id, SystemParameterDTO systemParameterDTO) throws ValidationException, ServiceException;

    public ResponseEntity<ApiResponseDto<?>> getAllSystemParameterDetails() throws ServiceException;

    public ResponseEntity<ApiResponseDto<?>> getSystemParameterDetails(HashMap map) throws ServiceException;

    public ResponseEntity<ApiResponseDto<?>> deleteSystemParameterDetails(Long id) throws ServiceException;
}

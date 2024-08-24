package com.example.springsecurity.services.impl;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.entity.SystemParameter;
import com.example.springsecurity.entityDTO.SystemParameterDTO;
import com.example.springsecurity.repository.SystemParameterRepository;
import com.example.springsecurity.services.SystemPrameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SystemParameterServiceImpl implements SystemPrameterService {

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveSystemParameterDetails(SystemParameterDTO systemParameterDTO) throws ValidationException, ServiceException {
        try {

            List<SystemParameter> systemParameterDTOList = systemParameterRepository.findBySysParamKey(systemParameterDTO.getSysParamKey());
            if (systemParameterDTOList != null && !systemParameterDTOList.isEmpty()) {
                throw new ValidationException("The system parameter " + systemParameterDTO.getSysParamKey() + " already exists. Please choose a different parameter name.");
            }
            SystemParameter systemParameter = new SystemParameter(systemParameterDTO);
            systemParameterRepository.save(systemParameter);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "System parameter " + systemParameterDTO.getSysParamKey() + " has been saved successfully."));

        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateSystemParameterDetails(Long id, SystemParameterDTO systemParameterDTO) throws ValidationException, ServiceException {
        try {
            Optional<SystemParameter> systemParameterDTOList = systemParameterRepository.findById(id);
            if (!systemParameterDTOList.isPresent()) {
                throw new ValidationException("The system parameter " + systemParameterDTO.getSysParamKey() + " is not exists. Please choose a different parameter name.");
            }

            SystemParameter systemParameter = systemParameterDTOList.get();
            systemParameter.setSysParamKey(systemParameterDTO.getSysParamKey());
            systemParameter.setSysParamDesc(systemParameterDTO.getSysParamDesc());
            systemParameter.setSysParamVal(systemParameterDTO.getSysParamVal());
            systemParameter.setInactive(systemParameterDTO.isInactive());

            systemParameterRepository.save(systemParameter);

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "System parameter " + systemParameterDTO.getSysParamDesc() + " has been successfully updated."));
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllSystemParameterDetails() throws ServiceException {
        try {
            List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), systemParameterList));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getSystemParameterDetails(HashMap map) throws ServiceException {
        try {
            List<SystemParameter> systemParameterList = systemParameterRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), systemParameterList));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteSystemParameterDetails(Long id) throws ServiceException {
        try {
            Optional<SystemParameter> systemParameterList = systemParameterRepository.findById(id);
            SystemParameter systemParameter = systemParameterList.get();
            systemParameterRepository.deleteById(systemParameter.getId());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "System parameter " + systemParameter.getSysParamKey() + " has been successfully deleted."));
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ServiceException();
        }
    }
}

package com.example.springsecurity.service.impl;

import com.example.springsecurity.applicationenum.ApiApplicationStatus;
import com.example.springsecurity.entity.ApplicationUsers;
import com.example.springsecurity.entityDTO.ApplicationUsersDTO;
import com.example.springsecurity.exceptions.ApplicationServiceException;
import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.repository.ApplicationUsersRepository;
import com.example.springsecurity.responseDTO.ApiResponseDTO;
import com.example.springsecurity.service.ApplicationUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ApplicationUsersServiceImpl implements ApplicationUsersService {

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private ApplicationUsersRepository applicationUsersRepository;

    @Value("{password.regex}")
    private String passwordRegexEnv;

    @Override
    public ResponseEntity<ApiResponseDTO<?>> saveUser(ApplicationUsersDTO applicationUsersDTO) throws ValidationException, ApplicationServiceException {
        try {
            Optional.ofNullable(applicationUsersRepository.findByEmail(applicationUsersDTO.getEmail())).orElseThrow(() -> {
                return new ValidationException("Registration Failed! Email already exists " + applicationUsersDTO.getEmail());
            });

            Optional.ofNullable(applicationUsersRepository.findByPhoneNumber(applicationUsersDTO.getPhoneNumber())).orElseThrow(() -> {
                return new ValidationException("Registration Failed! Phone number already exists " + applicationUsersDTO.getPhoneNumber());
            });
            String decodedPassword = new String(Base64.getDecoder().decode(applicationUsersDTO.getPassword()));
            validatePasswordPolicy(decodedPassword);

            ApplicationUsers applicationUsers = new ApplicationUsers(applicationUsersDTO.getEmail(), applicationUsersDTO.getPhoneNumber(), bcryptPasswordEncoder.encode(decodedPassword));
            applicationUsersRepository.save(applicationUsers);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>(ApiApplicationStatus.SUCCESS.name(), "User registration completed successfully!"));
        } catch (ValidationException exception) {
            throw new ValidationException(exception.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new ApplicationServiceException();
        }
    }


    public void validatePasswordPolicy(String password) throws ValidationException, ApplicationServiceException {
        try {
            if (!Pattern.matches(passwordRegexEnv, password)) {
                throw new ValidationException("Password must include at least one uppercase letter, one lowercase letter, one number, and one special character, and password must be at least 8 to 15 characters long.");
            }
        } catch (ValidationException exception) {
            throw new ValidationException(exception.getMessage());
        } catch (Exception exception) {
            throw new ApplicationServiceException();
        }
    }
}

package com.example.springsecurity.services.impl;

import com.example.springsecurity.Exception.*;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.constant.ApplicationConstant;
import com.example.springsecurity.entity.*;
import com.example.springsecurity.entityDTO.UserDTO;
import com.example.springsecurity.repository.SystemParameterRepository;
import com.example.springsecurity.repository.UserPasswordHistoryRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private @Lazy PasswordEncoder passwordEncoder;

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Autowired
    private UserPasswordHistoryRepository userPasswordHistoryRepository;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveUser(UserDTO userDTO) throws ValidationException, ServiceException {
        try {
            if (!userRepository.findByEmail(userDTO.getEmail()).isEmpty()) {
                throw new ValidationException("Registration Failed! Email already exists " + userDTO.getEmail());
            }

            if (!userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).isEmpty()) {
                throw new ValidationException("Registration Failed! Phone number already exists " + userDTO.getPhoneNumber());
            }

            User userDetails = new User(userDTO);

            String decodedPassword = new String(Base64.getDecoder().decode(userDTO.getPassword()));
            validatePasswordPolicy(decodedPassword);
            userDetails.setPassword(passwordEncoder.encode(decodedPassword));

            userDetails.setIsUserActive(true);
            Long passwordExpiryDays = Long.parseLong(systemParameterRepository.getSysParamValue(ApplicationConstant.DEFAULT_PASSWORD_EXPIRY_DAYS));
            userDetails.setPasswordExpiryDate(LocalDateTime.now().plusDays(passwordExpiryDays));
            Long passwordExpiryAlertDays = Long.parseLong(systemParameterRepository.getSysParamValue(ApplicationConstant.DEFAULT_PASSWORD_EXPIRY_ALERT_DAYS));
            userDetails.setPasswordExpiryAlertDate(LocalDateTime.now().plusDays(passwordExpiryAlertDays));
            userDetails.setUserType(ApplicationConstant.USER_TYPE_STANDARD);
            userDetails.setMaximumSessions(Integer.parseInt(systemParameterRepository.getSysParamValue(ApplicationConstant.DEFAULT_MAXIMUM_SESSION)));

            userDetails.setCurrentSessions(0);
            userDetails.setOnlineInd(false);
            userDetails.setInvalidAttempt(0);
            userDetails.setIsActLocked(false);

            UserProfile userProfile = new UserProfile();
            userProfile.setUser(userDetails);
            userDetails.setUserProfile(userProfile);

            UserPermission userPermission = new UserPermission();
            userPermission.setUser1(userDetails);
            userDetails.setUserPermission(userPermission);

            Address address = new Address();
            address.setUser2(userDetails);
            userDetails.setAddress(address);

            userRepository.save(userDetails);
            validatePasswordHistory(userDetails);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User registration completed successfully!"));
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getUser(String email, int page, int size, String sortBy, String sortOrder) throws ServiceException {
        try {
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            email = (email == null) ? "%" : email;
            Page<User> userList = userRepository.findByFilters(email, pageable);
            System.out.println(userList.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getUser(String email) throws ServiceException {
        try {
            Long userId = null;
            Optional<User> userList = userRepository.findByEmail(email);
            if (userList != null && !userList.isEmpty()) {
                User user = userList.get();
                userId = user.getId();
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userRepository.findById(userId)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(String username) throws ValidationException, ServiceException {
//        try {
//            if (userRepository.findByUserName(username) == null) {
//                throw new UserNotFoundException("User Not Exists! Kindly check username.");
//            }
//            User user = userRepository.findByUserName(username);
//            userRepository.deleteById(user.getId());
//            saveTransaction();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Deleted Successfully"));
//        } catch (UserNotFoundException userNotFoundException) {
////            saveTransaction();
//            throw new UserNotFoundException(userNotFoundException.getMessage());
//        } catch (Exception exception) {
////            saveTransaction();
//            exception.printStackTrace();
//            throw new UserServiceLogicException();
//        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(String username, User user) throws ValidationException, ServiceException {
//        try {
//            if (userRepository.findByUserName(username) == null) {
//                throw new UserNotFoundException("User Not Exists! Kindly check username.");
//            }
//            User userList = userRepository.findByUserName(username);
////            userList.setUserName(user.getUserName());
//            userList.setPassword(passwordEncoder.encode(user.getPassword()));
//            userList.setEmail(user.getEmail());
////            userList.setMobileNo(user.getMobileNo());
//            Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
//            userList.setModifiedDate(modifiedDate);
////            userList.setModifiedBy(user.getUserName());
//            userRepository.save(userList);
//            saveTransaction();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Updated Successfully"));
//        } catch (UserNotFoundException userNotFoundException) {
////            saveTransaction();
//            throw new UserNotFoundException(userNotFoundException.getMessage());
//        } catch (Exception exception) {
////            saveTransaction();
//            exception.printStackTrace();
//            throw new UserServiceLogicException();
//        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws ValidationException, ServiceException {
        try {
            List<User> userList = userRepository.findAll();
//            saveTransaction();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList));
        } catch (Exception exception) {
//            saveTransaction();
            exception.printStackTrace();
            throw new ServiceException();
        }
    }

    public void validatePasswordHistory(User userDetails) throws ValidationException, ServiceException {

        List<UserPasswordHistory> passwordHistoryList = Optional.ofNullable(userPasswordHistoryRepository.findByEmailAndUserId(userDetails.getEmail(), userDetails.getId())).orElseGet(ArrayList::new);
        if (passwordHistoryList.stream().anyMatch(history -> userDetails.getPassword().equals(history.getPassword()))) {
            throw new ValidationException("Error: The password you entered already exists. Please choose a different password.");
        }

        UserPasswordHistory userPasswordHistory = new UserPasswordHistory(userDetails.getId(), userDetails.getEmail(), userDetails.getPassword());
        userPasswordHistoryRepository.save(userPasswordHistory);
    }

    public void validatePasswordPolicy(String password) throws ValidationException, ServiceException {

        Integer passwordMinLength = Integer.parseInt(systemParameterRepository.getSysParamValue(ApplicationConstant.PASSWORD_MIN_LENGTH));
        Integer passwordMaxLength = Integer.parseInt(systemParameterRepository.getSysParamValue(ApplicationConstant.PASSWORD_MAX_LENGTH));
        String passwordRegexExp = systemParameterRepository.getSysParamValue(ApplicationConstant.PASSWORD_REGEX_EXPRESSION) + "{" + passwordMinLength + "," + passwordMaxLength + "}";

        if (password.length() < passwordMinLength || password.length() > passwordMaxLength) {
            throw new ValidationException("The password must be at least " + passwordMinLength + " - " + passwordMaxLength + " characters long.");
        }

        if (!Pattern.matches(passwordRegexExp.trim(), password)) {
            throw new ValidationException("Password must include at least one uppercase letter, one lowercase letter, one number, and one special character.");
        }

    }
}

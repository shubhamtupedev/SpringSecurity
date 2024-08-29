package com.example.springsecurity.services.impl;

import com.example.springsecurity.Exception.*;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.constant.ApplicationConstant;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entity.UserPasswordHistory;
import com.example.springsecurity.entityDTO.UserDTO;
import com.example.springsecurity.repository.SystemParameterRepository;
import com.example.springsecurity.repository.UserPasswordHistoryRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private @Lazy PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Autowired
    private UserPasswordHistoryRepository userPasswordHistoryRepository;

    @Value("${password.expiry.days}")
    private int passwordExpiryDays;

    @Value("${password.expiry.alert.days}")
    private int passwordExpiryAlertDays;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveUser(UserDTO userDTO) throws UserAlreadyExistsException, UserServiceLogicException {
        try {
//            userRepository.findByEmailOrPhoneNumber(userDTO.getEmail(), userDTO.getPhoneNumber()).ifPresent(user -> {
//                throw new UserAlreadyExistsException("Registration Failed! Email or Phone number already exists " + userDTO.getEmail() + " " + userDTO.getPhoneNumber());
//            });

            if (!userRepository.findByEmailOrPhoneNumber(userDTO.getEmail(), userDTO.getPhoneNumber()).isEmpty()) {
                throw new UserAlreadyExistsException("Registration Failed! Email or Phone number already exists " + userDTO.getEmail() + " " + userDTO.getPhoneNumber());
            }

            User userDetails = new User(userDTO);

            userDetails.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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

            userRepository.save(userDetails);

            validatePasswordHistory(userDetails);

            transactionServiceImpl.saveTransactionDetails(userDetails.getDtlId(), "saveUser", this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1), "User registration completed successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User registration completed successfully!"));
        } catch (UserAlreadyExistsException e) {
//            saveTransaction();
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
//            saveTransaction();
            e.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getUser(String username) throws UserNotFoundException, UserServiceLogicException {
//        try {
//            if (userRepository.findByUserName(username) == null) {
//                throw new UserNotFoundException("User Not Exists! Kindly check username.");
//            }
//            User user = userRepository.findByUserName(username);
//            saveTransaction();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), null));
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
    public ResponseEntity<ApiResponseDto<?>> deleteUser(String username) throws UserNotFoundException, UserServiceLogicException {
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
    public ResponseEntity<ApiResponseDto<?>> updateUser(String username, User user) throws UserNotFoundException, UserServiceLogicException {
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
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        try {
            List<User> userList = userRepository.findAll();
//            saveTransaction();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList));
        } catch (Exception exception) {
//            saveTransaction();
            exception.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    public void validatePasswordHistory(User userDetails) throws ValidationException, ServiceException {
        List<UserPasswordHistory> passwordHistoryList = userPasswordHistoryRepository.findByEmailAndUserId(userDetails.getEmail(), userDetails.getId());
        if (passwordHistoryList != null && !passwordHistoryList.isEmpty()) {
            Iterator<UserPasswordHistory> iterator = passwordHistoryList.iterator();
            while (iterator.hasNext()) {
                UserPasswordHistory userPasswordHistory = iterator.next();
                if (userDetails.getPassword().equals(userPasswordHistory.getPassword())) {
                    throw new ValidationException("Error: The password you entered already exists. Please choose a different password.");
                }
            }
        }

        UserPasswordHistory userPasswordHistory = new UserPasswordHistory();
        userPasswordHistory.setUserId(userDetails.getId());
        userPasswordHistory.setEmail(userDetails.getEmail());
        userPasswordHistory.setPassword(userDetails.getPassword());
        userPasswordHistoryRepository.save(userPasswordHistory);

    }
}

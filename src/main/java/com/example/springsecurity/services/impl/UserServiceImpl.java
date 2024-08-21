package com.example.springsecurity.services.impl;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserNotFoundException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.config.UserPrincipal;
import com.example.springsecurity.entity.Transaction;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.TransactionRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private @Lazy PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${password.expiry.days}")
    private int passwordExpiryDays;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveUser(User user) throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            if (userRepository.findByUserName(user.getUserName()) != null) {
                throw new UserAlreadyExistsException("Registration Failed! Username already exists " + user.getUserName());
            }
            if (userRepository.findByemailId(user.getEmailId()) != null) {
                throw new UserAlreadyExistsException("Registration Failed! Email already exists " + user.getEmailId());
            }
            Timestamp passwordExpiryDate = new Timestamp(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_WEEK, passwordExpiryDays);
            passwordExpiryDate.setTime(calendar.getTime().getTime());
            user.setPasswordExpiryDate(passwordExpiryDate);
            User userDetails = new User(null, user.getUserName(), passwordEncoder.encode(user.getPassword()), true, passwordExpiryDate, user.getEmailId(), user.getMobileNo(), false);
            userDetails.setCreatedBy(user.getUserName());
            Timestamp createdDate = new Timestamp(System.currentTimeMillis());
            userDetails.setCreatedDate(createdDate);
            userRepository.save(userDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Registration Successfull"));
        } catch (UserAlreadyExistsException e) {
            Transaction transaction = new Transaction(null, UUID.randomUUID().toString(), e.getMessage(), "saveUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getUser(String username) throws UserNotFoundException, UserServiceLogicException {
        try {
            if (userRepository.findByUserName(username) == null) {
                throw new UserNotFoundException("User Not Exists! Kindly check username.");
            }
            User user = userRepository.findByUserName(username);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), user));
        } catch (UserNotFoundException userNotFoundException) {
            Transaction transaction = new Transaction(null, UUID.randomUUID().toString(), userNotFoundException.getMessage(), "getUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            throw new UserNotFoundException(userNotFoundException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UserServiceLogicException();
        }
    }


    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(String username) throws UserNotFoundException, UserServiceLogicException {
        try {
            if (userRepository.findByUserName(username) == null) {
                throw new UserNotFoundException("User Not Exists! Kindly check username.");
            }
            User user = userRepository.findByUserName(username);
            userRepository.deleteById(user.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Deleted Successfully"));
        } catch (UserNotFoundException userNotFoundException) {
            Transaction transaction = new Transaction(null, UUID.randomUUID().toString(), userNotFoundException.getMessage(), "deleteUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            throw new UserNotFoundException(userNotFoundException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(String username, User user) throws UserNotFoundException, UserServiceLogicException {
        try {
            if (userRepository.findByUserName(username) == null) {
                throw new UserNotFoundException("User Not Exists! Kindly check username.");
            }
            User userList = userRepository.findByUserName(username);
            userList.setUserName(user.getUserName());
            userList.setPassword(passwordEncoder.encode(user.getPassword()));
            userList.setEmailId(user.getEmailId());
            userList.setMobileNo(user.getMobileNo());
            Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
            userList.setModifiedDate(modifiedDate);
            userList.setModifiedBy(user.getUserName());
            userRepository.save(userList);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Updated Successfully"));
        } catch (UserNotFoundException userNotFoundException) {
            Transaction transaction = new Transaction(null, UUID.randomUUID().toString(), userNotFoundException.getMessage(), "updateUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            throw new UserNotFoundException(userNotFoundException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        try {
            List<User> userList = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList));
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByUserName(username) == null) {
            throw new UsernameNotFoundException("User Not Exists! Kindly check username.");
        }
        User user = userRepository.findByUserName(username);
        return new UserPrincipal(user);
    }
}

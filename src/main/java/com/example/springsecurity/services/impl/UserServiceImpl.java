package com.example.springsecurity.services.impl;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserNotFoundException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.ResponseDTO.ApiResponseDto;
import com.example.springsecurity.ResponseDTO.ApiResponseStatus;
import com.example.springsecurity.config.UserPrincipal;
import com.example.springsecurity.entity.Transaction;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entityDTO.UserDTO;
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
    private TransactionServiceImpl transactionServiceImpl;

    @Value("${password.expiry.days}")
    private int passwordExpiryDays;

    @Value("${password.expiry.alert.days}")
    private int passwordExpiryAlertDays;

    @Override
    public ResponseEntity<ApiResponseDto<?>> saveUser(UserDTO userDTO) throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            if (userRepository.findByUserName(userDTO.getUserName()) != null) {
                throw new UserAlreadyExistsException("Registration Failed! Username already exists " + userDTO.getUserName());
            }
            if (userRepository.findByemail(userDTO.getEmail()) != null) {
                throw new UserAlreadyExistsException("Registration Failed! Email already exists " + userDTO.getEmail());
            }

            User userDetails = new User(userDTO);

            userDetails.setCurrentSessions(Long.valueOf(0));
            userDetails.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            Timestamp passwordExpiryDate = new Timestamp(System.currentTimeMillis());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_WEEK, passwordExpiryDays);
            passwordExpiryDate.setTime(calendar.getTime().getTime());
            userDetails.setPasswordExpiryDate(passwordExpiryDate);

            Timestamp passwordExpiryAlertDate = new Timestamp(System.currentTimeMillis());
            Calendar calendarr = Calendar.getInstance();
            calendarr.add(Calendar.DAY_OF_WEEK, passwordExpiryAlertDays);
            passwordExpiryAlertDate.setTime(calendarr.getTime().getTime());
            userDetails.setPasswordExpiryAlertDate(passwordExpiryAlertDate);

            userDetails.setInactive(false);
            userDetails.setIsAcntEnabled(true);
            userDetails.setGraceLongRemaining(Long.valueOf(0));
            userDetails.setMaximumSessions(Long.valueOf(10));
            userDetails.setOnlineInd(false);
            Long dtlId = transactionServiceImpl.getMaxDtlId();
            userDetails.setDtlId(dtlId);
            userDetails.setCreatedBy(userDTO.getUserName());

            Timestamp createdDate = new Timestamp(System.currentTimeMillis());
            userDetails.setCreatedDate(createdDate);
            userRepository.save(userDetails);

            saveTransaction();
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Registration Successfull"));
        } catch (UserAlreadyExistsException e) {
            saveTransaction();
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            saveTransaction();
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
            saveTransaction();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), user));
        } catch (UserNotFoundException userNotFoundException) {
            saveTransaction();
            throw new UserNotFoundException(userNotFoundException.getMessage());
        } catch (Exception exception) {
            saveTransaction();
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
            userRepository.deleteById(user.getId());
            saveTransaction();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Deleted Successfully"));
        } catch (UserNotFoundException userNotFoundException) {
            saveTransaction();
            throw new UserNotFoundException(userNotFoundException.getMessage());
        } catch (Exception exception) {
            saveTransaction();
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
            userList.setEmail(user.getEmail());
            userList.setMobileNo(user.getMobileNo());
            Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
            userList.setModifiedDate(modifiedDate);
            userList.setModifiedBy(user.getUserName());
            userRepository.save(userList);
            saveTransaction();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), "User Updated Successfully"));
        } catch (UserNotFoundException userNotFoundException) {
            saveTransaction();
            throw new UserNotFoundException(userNotFoundException.getMessage());
        } catch (Exception exception) {
            saveTransaction();
            exception.printStackTrace();
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        try {
            List<User> userList = userRepository.findAll();
            saveTransaction();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), userList));
        } catch (Exception exception) {
            saveTransaction();
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


    public void saveTransaction() {
        transactionServiceImpl.saveTransactionDetails(transactionServiceImpl.getMaxDtlId(), null, null, null, null, null, null, "Success");
    }
}

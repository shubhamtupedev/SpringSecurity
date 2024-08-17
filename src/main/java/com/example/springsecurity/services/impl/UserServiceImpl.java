package com.example.springsecurity.services.impl;

import com.example.springsecurity.entity.Transaction;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.TransactionRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${password.expiry.days}")
    private int passwordExpiryDays;

    @Override
    public String saveUser(User user) {
        String result = "";
        try {
            Optional<User> userlist = userRepository.findByUsername(user.getUserName());
            if (!userlist.isPresent()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setAcntEnabled(true);
                Timestamp passwordExpiryDate = new Timestamp(System.currentTimeMillis());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_WEEK, passwordExpiryDays);
                passwordExpiryDate.setTime(calendar.getTime().getTime());
                user.setPasswordExpiryDate(passwordExpiryDate);
                user.setCreatedBy(user.getUserName());
                user.setCreatedDate(passwordExpiryDate);
                userRepository.save(user);
                result = "User Successfully Saved";
            } else {
                result = "Username Already Exists!";
            }
        } catch (Exception e) {
            result = "Something went wrong! Please contact administrator.";
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), e.getMessage(), "saveUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getUser(String username) {
        List userListResult = new ArrayList();
        try {
            Optional<User> userlist = userRepository.findByUsername(username);
            if (userlist.isPresent()) {
                userListResult.add(userlist.get());
            } else {
                userListResult.add("Username is not Present. Please check username.");
            }
        } catch (Exception e) {
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), e.getMessage(), "getUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            e.printStackTrace();
        }

        return userListResult;
    }

    @Override
    public String deleteUser(String username) {
        String result = "";
        try {
            Optional<User> userlist = userRepository.findByUsername(username);
            if (userlist.isPresent()) {
                userRepository.deleteById(userlist.get().getUserId());
                result = "User Deleted Successfully";
            } else {
                result = "Username is not Present. Please check username.";
            }
        } catch (Exception e) {
            result = "Something went wrong! Please contact administrator.";
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), e.getMessage(), "deleteUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String updateUser(String username, User user) {
        String result = "";
        try {
            Optional<User> userlist = userRepository.findByUsername(username);
            if (userlist.isPresent()) {
                userlist.get().setUserName(user.getUserName());
                userlist.get().setPassword(passwordEncoder.encode(user.getPassword()));
                result = "User updated Successfully";
            } else {
                result = "Username is not Present. Please check username.";
            }
        } catch (Exception e) {
            result = "Something went wrong! Please contact administrator.";
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), e.getMessage(), "updateUser", "UserServiceImpl");
            transactionRepository.save(transaction);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            userList = userRepository.findAll();
        } catch (Exception e) {
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), e.getMessage(), "getAllUsers", "UserServiceImpl");
            transactionRepository.save(transaction);
            e.printStackTrace();
        }
        return userList;
    }
}

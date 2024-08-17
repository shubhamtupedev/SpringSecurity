package com.example.springsecurity.services.impl;

import com.example.springsecurity.entity.Transaction;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.TransactionRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String saveUser(User user) {
        String result = "";
        try {
            userRepository.save(user);
            result = "User Successfully Saved";
        } catch (Exception e) {
            result = "Something went wrong! Please contact administrator.";
            Transaction transaction = new Transaction(UUID.randomUUID().toString(),e.getMessage(),"saveUser","UserServiceImpl");
            transactionRepository.save(transaction);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> getUser(String username) {
        return null;
    }

    @Override
    public String deleteUser(String username) {
        return null;
    }

    @Override
    public String updateUser(Long id, String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}

package com.example.springsecurity.services.impl;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(User user) {
        String result = "";
        try {
            userRepository.save(user);
            result = "User Successfully Saved";
        } catch (Exception e) {
            result = "Something went wrong! Please contact administrator.";
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

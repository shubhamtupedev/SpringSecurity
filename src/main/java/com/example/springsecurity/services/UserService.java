package com.example.springsecurity.services;

import com.example.springsecurity.entity.User;

import java.util.List;

public interface UserService {
    public String saveUser(User user);

    public List<User> getUser(String username);

    public String deleteUser(String username);

    public String updateUser(Long id, String username);

    public List<User> getAllUsers();

}

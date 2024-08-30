package com.example.springsecurity.services.impl;

import com.example.springsecurity.config.UserPrincipal;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userList = userRepository.findByEmail(email);
        if (userList.isEmpty()) {
            throw new UsernameNotFoundException("User Not Exists! Kindly check email.");
        }
        return new UserPrincipal(userList.get());
    }
}

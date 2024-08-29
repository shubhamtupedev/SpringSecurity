package com.example.springsecurity.services.impl;

import com.example.springsecurity.config.UserPrincipal;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        User user = userRepository.findByUserName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User Not Exists! Kindly check username.");
//        }
        return new UserPrincipal(null);
    }
}

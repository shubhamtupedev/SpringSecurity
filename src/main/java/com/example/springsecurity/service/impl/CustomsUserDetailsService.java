package com.example.springsecurity.service.impl;

import com.example.springsecurity.entity.ApplicationUsers;
import com.example.springsecurity.entity.ApplicationUsersPrincipal;
import com.example.springsecurity.repository.ApplicationUsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomsUserDetailsService implements UserDetailsService {

    private ApplicationUsersRepository applicationUsersRepository;

    public CustomsUserDetailsService(ApplicationUsersRepository applicationUsersRepository) {
        this.applicationUsersRepository = applicationUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<ApplicationUsers> optionalApplicationUsers = applicationUsersRepository.findByEmail(username.toUpperCase().trim());
            if (optionalApplicationUsers.isEmpty()) {
                throw new UsernameNotFoundException("Email not exists! Kindly check email. " + username);
            }
            return new ApplicationUsersPrincipal(optionalApplicationUsers.get());
        } catch (UsernameNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage());
        }
    }
}

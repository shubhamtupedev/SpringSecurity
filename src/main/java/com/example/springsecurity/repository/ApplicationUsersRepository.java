package com.example.springsecurity.repository;

import com.example.springsecurity.entity.ApplicationUsers;
import com.example.springsecurity.service.ApplicationUsersService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUsersRepository extends JpaRepository<ApplicationUsers, Long> {
    public Optional<ApplicationUsers> findByEmail(String email);

    public Optional<ApplicationUsers> findByPhoneNumber(String phoneNumber);
}

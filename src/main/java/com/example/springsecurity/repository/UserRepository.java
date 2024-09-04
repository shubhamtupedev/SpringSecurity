package com.example.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    public Optional<User> findByPhoneNumber(@Param("phoneNumber") String phoneNumber) throws ValidationException, ServiceException;

    @Query("SELECT u FROM User u WHERE :email IS NULL OR u.email LIKE %:email%")
    Page<User> findByFilters(@Param("email") String email, Pageable pageable);
}

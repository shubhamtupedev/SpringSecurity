package com.example.springsecurity.repository;

import com.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "FROM User WHERE userName= ?1")
    public User findByUsername(String userName);

    @Query(value = "FROM User where emailId= ?1")
    public User findByEmail(String emailId);
}

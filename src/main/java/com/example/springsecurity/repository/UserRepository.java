package com.example.springsecurity.repository;

import com.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u where u.usr_name= :username")
    public User findByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User u where u.email_id= :emailId")
    public User findByEmail(@Param("emailId") String emailId);
}

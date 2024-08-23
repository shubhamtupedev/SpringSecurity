package com.example.springsecurity.repository;

import com.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query(value = "FROM User WHERE userName= ?1")
    public User findByUserName(String userName);

    //    @Query(value = "FROM User where emailId= ?1")
    public User findByemail(String email);
}

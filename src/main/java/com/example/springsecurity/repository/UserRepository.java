package com.example.springsecurity.repository;

import com.example.springsecurity.Exception.UserAlreadyExistsException;
import com.example.springsecurity.Exception.UserServiceLogicException;
import com.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query(value = "FROM User WHERE userName= ?1")
//    public User findByUserName(String userName);

    //    @Query(value = "FROM User where emailId= ?1")
//    public User findByemail(String email);

//    public User findByMobileNo(String mobileNo);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.phoneNumber = :phoneNumber")
    public Optional<User> findByEmailOrPhoneNumber(@Param("email") String email, @Param("phoneNumber") String phoneNumber) throws UserAlreadyExistsException, UserServiceLogicException;
}

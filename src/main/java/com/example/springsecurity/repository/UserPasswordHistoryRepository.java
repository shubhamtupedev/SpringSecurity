package com.example.springsecurity.repository;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Exception.ValidationException;
import com.example.springsecurity.entity.UserPasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPasswordHistoryRepository extends JpaRepository<UserPasswordHistory, Long> {

    public List<UserPasswordHistory> findByEmailAndUserId(String userName, Long userId) throws ValidationException, ServiceException;
}

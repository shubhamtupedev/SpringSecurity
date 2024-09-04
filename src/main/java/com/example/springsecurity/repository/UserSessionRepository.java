package com.example.springsecurity.repository;

import com.example.springsecurity.entity.UserSessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSessionInfo, Long> {
}

package com.example.springsecurity.services.impl;

import com.example.springsecurity.Utility.SessionIdGenerateUtil;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.entity.UserSessionInfo;
import com.example.springsecurity.repository.UserSessionRepository;
import com.example.springsecurity.services.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Autowired
    private UserSessionRepository userSessionRepository;


    enum SessionStatus {
        ACTIVE,
        LOGGED_OUT,
        EXPIRED
    }

    @Override
    public void saveUserSessionDetails(User user) {
        try {
            UserSessionInfo userSessionInfo = new UserSessionInfo(SessionIdGenerateUtil.generateSessionId(), user.getEmail(), user.getId(), LocalDateTime.now(), LocalDateTime.now(), "192.168.0.1", "", "", LocalDateTime.now(), LocalDateTime.now(), SessionStatus.ACTIVE.name(), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

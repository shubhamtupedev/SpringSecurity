package com.example.springsecurity.services.impl;

import com.example.springsecurity.entity.Audit;
import com.example.springsecurity.repository.AuditRepository;
import com.example.springsecurity.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public void saveLog(String endpoint, byte[] requestPayload, byte[] responsePayload, String error) {
        auditRepository.save(new Audit(null, endpoint, requestPayload, responsePayload, error, LocalDateTime.now()));
    }
}

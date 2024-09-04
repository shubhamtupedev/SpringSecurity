package com.example.springsecurity.services.impl;

import com.example.springsecurity.Exception.ServiceException;
import com.example.springsecurity.Utility.CompressionUtils;
import com.example.springsecurity.entity.Audit;
import com.example.springsecurity.repository.AuditRepository;
import com.example.springsecurity.services.AuditService;
import com.example.springsecurity.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditRepository;

    @Override
    public void saveAuditDetails(String endpoint, String requestPayload, String responsePayload, String error, String ipAddress) throws ServiceException {
        auditRepository.save(new Audit(endpoint, CompressionUtils.compress(requestPayload), CompressionUtils.compress(responsePayload), error, LocalDateTime.now(), ipAddress));
    }
}

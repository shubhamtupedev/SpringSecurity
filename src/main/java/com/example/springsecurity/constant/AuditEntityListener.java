package com.example.springsecurity.constant;

import com.example.springsecurity.entity.AuditableModel;
import com.example.springsecurity.repository.TransactionRepository;
import com.example.springsecurity.services.TransactionService;
import com.example.springsecurity.services.impl.TransactionServiceImpl;
import jakarta.persistence.PrePersist;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.Timestamp;

@Component
public class AuditEntityListener {

    @PrePersist
    public void prePersist(AuditableModel model) {
        model.setCreatedBy("System_User");
        Timestamp createdDateTimestamp = new Timestamp(System.currentTimeMillis());
        model.setCreatedDate(createdDateTimestamp);

        SecureRandom secureRandom = new SecureRandom();
        model.setDtlId(Math.abs(secureRandom.nextLong()));
    }
}

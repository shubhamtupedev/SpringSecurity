package com.example.springsecurity.services;


public interface AuditService {
    public void saveLog(String endpoint, byte[] requestPayload, byte[] responsePayload, String error);
}

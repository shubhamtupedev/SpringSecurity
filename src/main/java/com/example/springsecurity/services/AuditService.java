package com.example.springsecurity.services;


import com.example.springsecurity.Exception.ServiceException;

public interface AuditService {
    public void saveAuditDetails(String endpoint, String requestPayload, String responsePayload, String error, String ipAddress) throws ServiceException;
}

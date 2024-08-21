package com.example.springsecurity.services;

public interface TransactionService {

    public void saveTransactionDetails(Long transactionId, String methodname, String classname, String request, String endpoint, String clientIpAddress, String response, String trnmessage);

    public Long getMaxDtlId();
}

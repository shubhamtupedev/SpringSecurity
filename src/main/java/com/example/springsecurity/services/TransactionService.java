package com.example.springsecurity.services;

public interface TransactionService {

    public void saveTransactionDetails(Long transactionId, String methodName, String className, String trnMessage);

    public Long getMaxDtlId();
}

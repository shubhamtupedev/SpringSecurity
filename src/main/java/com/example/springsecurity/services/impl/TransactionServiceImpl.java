package com.example.springsecurity.services.impl;

import com.example.springsecurity.entity.Transaction;
import com.example.springsecurity.repository.TransactionRepository;
import com.example.springsecurity.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void saveTransactionDetails(Long transactionId, String methodname, String classname, String request, String endpoint, String clientIpAddress, String response, String trnmessage) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setMethodName(methodname);
        transaction.setClassName(classname);
        transaction.setRequest(request);
        transaction.setEndpoint(endpoint);
        transaction.setClientIPAddress(clientIpAddress);
        transaction.setResponse(response);
        transaction.setTrnMessage(trnmessage);
        transactionRepository.save(transaction);

    }

    @Override
    public Long getMaxDtlId() {
        Long maxId = null;
        try {
            Long maxDtlId = transactionRepository.getMaxtransactionId();
            if (maxDtlId == null) {
                maxDtlId = Long.valueOf(0);
            }
            maxId = (maxDtlId == 0) ? maxDtlId++ : maxDtlId;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return maxId;
    }


}

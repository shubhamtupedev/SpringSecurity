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
    public void saveTransactionDetails(Long transactionId, String methodName, String className, String trnMessage) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionId);
        transaction.setMethodName(methodName);
        transaction.setClassName(className);
        transaction.setTrnMessage(trnMessage);
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

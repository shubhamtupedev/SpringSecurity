package com.example.springsecurity.repository;

import com.example.springsecurity.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT MAX(transactionId) FROM Transaction")
    public Long getMaxtransactionId();
}

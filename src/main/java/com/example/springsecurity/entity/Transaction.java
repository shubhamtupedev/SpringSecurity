package com.example.springsecurity.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "gen_db_transaction_log")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "error_msg")
    private String errorMessage;

    @Column(name = "method_name")
    private String methodName;
    @Column(name = "class_name")
    private String className;


    public Transaction() {
    }

    public Transaction(Long id, String transactionId, String errorMessage, String methodName, String className) {
        this.id = id;
        this.transactionId = transactionId;
        this.errorMessage = errorMessage;
        this.methodName = methodName;
        this.className = className;
    }

    public Transaction(String transactionId, String errorMessage, String methodName, String className) {
        this.transactionId = transactionId;
        this.errorMessage = errorMessage;
        this.methodName = methodName;
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}

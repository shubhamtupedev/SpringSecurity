package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "MST_DB_TRANSACTION_LOG")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "METHOD_NAME")
    private String methodName;

    @Column(name = "CLASS_NAME")
    private String className;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "ENDPOINT")
    private String endpoint;

    @Column(name = "CLIENT_IP_ADDRESS")
    private String clientIPAddress;

    @Column(name = "RESPONSE")
    private String response;

    @Column(name = "TRANSACTION_MSG")
    private String trnMessage;
}

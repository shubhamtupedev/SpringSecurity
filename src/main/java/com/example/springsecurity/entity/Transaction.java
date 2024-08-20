package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "gen_db_transaction_log")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}

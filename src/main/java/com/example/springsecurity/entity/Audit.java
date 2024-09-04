package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUDIT_LOG")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AD_ID")
    private Long id;

    @Column(name = "END_POINT")
    private String endPoint;

    @Column(name = "REQUEST_PAYLOAD")
    public byte[] requestPayload;

    @Column(name = "RESPONSE_PAYLOAD")
    private byte[] responsePayload;

    @Column(name = "ERROR")
    private String error;

    @Column(name = "LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    public Audit(String endPoint, byte[] requestPayload, byte[] responsePayload, String error, LocalDateTime localDateTime, String ipAddress) {
        this.endPoint = endPoint;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
        this.error = error;
        this.localDateTime = localDateTime;
        this.ipAddress = ipAddress;
    }
}

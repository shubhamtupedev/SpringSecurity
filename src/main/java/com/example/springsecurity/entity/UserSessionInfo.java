package com.example.springsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYSTEM_USER_SESSION_INFO")
public class UserSessionInfo extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "US_ID")
    private int id;

    @Column(name = "SESSION_ID")
    @NotBlank
    private String sessionId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "LOGIN_TIME")
    private LocalDateTime loginTime;

    @Column(name = "LAST_ACCESSED_DATE_TIME")
    private LocalDateTime lastAccessedTime;

    @Column(name = "CLIENT_IP_ADDS")
    private String clientIpAddress;

    @Column(name = "USER_AGENT")
    private String userAgent; //Browser or client details

    @Column(name = "PC_USER")
    private String pcUser;

    @Column(name = "LOGIN_DATE_TIME")
    private LocalDateTime loginDateTime;

    @Column(name = "LOGOUT_DATE_TIME")
    private LocalDateTime logoutDateTime;

    @Column(name = "SESSION_STATUS")
    private String status;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    public UserSessionInfo(String sessionId, String email, Long userId, LocalDateTime loginTime, LocalDateTime lastAccessedTime, String clientIpAddress, String userAgent, String pcUser, LocalDateTime loginDateTime, LocalDateTime logoutDateTime, String status, String deviceType) {
        this.sessionId = sessionId;
        this.email = email;
        this.userId = userId;
        this.loginTime = loginTime;
        this.lastAccessedTime = lastAccessedTime;
        this.clientIpAddress = clientIpAddress;
        this.userAgent = userAgent;
        this.pcUser = pcUser;
        this.loginDateTime = loginDateTime;
        this.logoutDateTime = logoutDateTime;
        this.status = status;
        this.deviceType = deviceType;
    }
}


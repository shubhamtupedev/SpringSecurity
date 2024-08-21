package com.example.springsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_SESSION_INFO", uniqueConstraints = {

})
public class UserSessionInfo extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USI_ID")
    private int id;

    @Column(name = "SESSION_ID")
    @NotBlank
    private String sessionId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "LOGIN_TIME")
    private Timestamp loginTime;

    @Column(name = "LAST_ACCESSED_TIME")
    private Timestamp lastAccessedTime;

    @Column(name = "CLIENT_IP_ADRS")
    private String clientIpAddress;

    @Column(name = "PC_USER")
    private String pcUser;


}

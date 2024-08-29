package com.example.springsecurity.entity;

import com.example.springsecurity.Utility.BooleanToStringConverter;
import com.example.springsecurity.entityDTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL"), @UniqueConstraint(columnNames = "MOBILE_NUMBER")})
public class User extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "USER_PWD")
    private String password;

    @Column(name = "USER_ACTIVE")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isUserActive;

    @Column(name = "PWD_EXPIRY_DATE")
    private LocalDateTime passwordExpiryDate;

    @Column(name = "PWD_EXPIRY_ALERT_DATE")
    private LocalDateTime passwordExpiryAlertDate;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "MAX_SESSIONS")
    private Integer maximumSessions;

    @Column(name = "CURRENT_SESSIONS")
    private Integer currentSessions;

    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @Column(name = "ONLINE_IND")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean onlineInd;

    @Column(name = "LAST_LOGOUT_DATE")
    private LocalDateTime lastLogoutDate;

    @Column(name = "INVALID_ATTEMPT")
    private Integer invalidAttempt;

    @Column(name = "ACT_LOCKED")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isActLocked;

    public User(UserDTO userDTO) {
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.phoneNumber = userDTO.getPhoneNumber();
    }
}

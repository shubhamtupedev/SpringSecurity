package com.example.springsecurity.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "GEN_MST_USR_LST")
public class User extends AuditableModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "usr_name")
    private String userName;

    @Column(name = "usr_pwd")
    private String password;

    @Column(name = "acnt_enable")
    private Boolean isAcntEnabled;

    @Column(name = "pwd_expiry_date")
    private Timestamp passwordExpiryDate;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "mobile_number")
    private String mobileNo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAcntEnabled() {
        return isAcntEnabled;
    }

    public void setAcntEnabled(Boolean acntEnabled) {
        isAcntEnabled = acntEnabled;
    }

    public Timestamp getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(Timestamp passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public User(Long userId, String userName, String password, Boolean isAcntEnabled, Timestamp passwordExpiryDate, String emailId, String mobileNo) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.isAcntEnabled = isAcntEnabled;
        this.passwordExpiryDate = passwordExpiryDate;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isAcntEnabled=" + isAcntEnabled +
                ", passwordExpiryDate=" + passwordExpiryDate +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}

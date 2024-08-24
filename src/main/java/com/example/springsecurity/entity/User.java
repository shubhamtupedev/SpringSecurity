package com.example.springsecurity.entity;

import com.example.springsecurity.Utility.BooleanToStringConverter;
import com.example.springsecurity.entityDTO.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MST_USR_LST", uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_NAME"),
        @UniqueConstraint(columnNames = "EMAIL"),
        @UniqueConstraint(columnNames = "MOBILE_NUMBER")
})
public class User extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PWD")
    private String password;

    @Column(name = "ACNT_ENABLE")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isAcntEnabled;

    @Column(name = "PWD_EXPIRY_DATE")
    private Timestamp passwordExpiryDate;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNo;

    @Column(name = "INACTIVE")
    @Convert(converter = BooleanToStringConverter.class)
    private boolean inactive;

    @Column(name = "PWD_EXPIRY_ALERT_DATE")
    private Timestamp passwordExpiryAlertDate;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_TYPE")
    private String userType;

    @Column(name = "USER_DOB")
    private Date dateOfBirth;

    @Column(name = "MAX_SESSIONS")
    private Integer maximumSessions;

    @Column(name = "CURRENT_SESSIONS")
    private Integer currentSessions;

    @Column(name = "LAST_LOGIN_DATE")
    private Timestamp lastLoginDate;

    @Column(name = "ONLINE_IND")
    @Convert(converter = BooleanToStringConverter.class)
    private boolean onlineInd;

    @Column(name = "LAST_LOGOUT_DATE")
    private Timestamp lastLogoutDate;

    @Column(name = "OTP")
    private String otp;

    @Column(name = "OTP_VALID_UNTIL")
    private Timestamp otpValidUntil;

    @Column(name = "INVALID_ATMPT")
    private Integer invalidAttempt;

    public User(UserDTO userDTO) {
        this.userName = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.mobileNo = userDTO.getMobileNo();
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.dateOfBirth = userDTO.getDateOfBirth();
    }
}

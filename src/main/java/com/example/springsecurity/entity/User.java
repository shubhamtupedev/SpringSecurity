package com.example.springsecurity.entity;

import com.example.springsecurity.Utility.BooleanToStringConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.io.Serializable;
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

    @NotBlank(message = "Username is required!")
    @Size(min = 3, message = "Username must have atleast 3 characters!")
    @Size(max = 15, message = "Username can have almost 15 characters!")
    @Pattern(regexp = "[A-Za-z]+", message = "Username must contain only characters!")
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PWD")
    @Size(min = 8, message = "Password should be minimum 8 characters!")
    @Size(max = 15, message = "Password should be upto 15 characters!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]+$", message = "Password must be include at least one uppercase letter, one lowercase letter, one number, and one special character.")
    @NotBlank(message = "Password is required!")
    private String password;

    @Column(name = "ACNT_ENABLE")
    @Convert(converter = BooleanToStringConverter.class)
    private Boolean isAcntEnabled;

    @Column(name = "PWD_EXPIRY_DATE")
    private Timestamp passwordExpiryDate;

    @Column(name = "EMAIL")
    @Email(message = "Email is not valid format!")
    @NotBlank(message = "Email is required!")
    private String emailId;

    @Column(name = "MOBILE_NUMBER")
    @NotBlank(message = "Mobile number is required!")
    @Size(min = 10, max = 10, message = "Mobile number must have 10 characters!")
    @Pattern(regexp = "^[0-9]*$", message = "Mobile number must contains only digits!")
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

    @Column(name = "SALUTATION")
    private String salutation;

    @Column(name = "USER_DOB")
    private Timestamp dateOfBirth;

    @Column(name = "MAX_SESSIONS")
    private Long maximumSessions;

    @Column(name = "CURRENT_SESSIONS")
    private Long currentSessions;

    @Column(name = "LAST_LOGIN_DATE")
    private Timestamp lastLoginDate;

    @Column(name = "ONLINE_IND")
    @Convert(converter = BooleanToStringConverter.class)
    private boolean onlineInd;

    @Column(name = "GRACE_LOGINS_RMNG")
    private Long graceLongRemaining;

    @Column(name = "LAST_LOGOUT_DATE")
    private Timestamp lastLogoutDate;

    @Column(name = "OTP")
    private String otp;

    @Column(name = "OTP_VALID_UNTIL")
    private Timestamp otpValidUntil;

}

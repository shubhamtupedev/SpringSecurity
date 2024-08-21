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
@Table(name = "GEN_MST_USR_LST", uniqueConstraints = {
        @UniqueConstraint(columnNames = "USER_NAME"),
        @UniqueConstraint(columnNames = "EMAIL")
})
public class User extends AuditableModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @NotBlank(message = "Username is required!")
    @Size(min = 3, message = "Username must have atleast 3 characters!")
    @Size(max = 15, message = "Username can have almost 15 characters!")
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PWD")
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
}

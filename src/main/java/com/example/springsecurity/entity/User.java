package com.example.springsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GEN_MST_USR_LST", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email_id")
})
public class User extends AuditableModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required!")
    @Size(min = 3, message = "Username must have atleast 3 characters!")
    @Size(max = 15, message = "Username can have almost 15 characters!")
    @Column(name = "usr_name")
    private String userName;

    @Column(name = "usr_pwd")
    @NotBlank(message = "Password is required!")
    private String password;

    @Column(name = "acnt_enable")
    private Boolean isAcntEnabled;

    @Column(name = "pwd_expiry_date")
    private Timestamp passwordExpiryDate;

    @Column(name = "email_id")
    @Email(message = "Email is not valid format!")
    @NotBlank(message = "Email is required!")
    private String emailId;

    @Column(name = "mobile_number")
    @NotBlank(message = "Mobile number is required!")
    @Size(min = 10, max = 10, message = "Mobile number must have 10 characters!")
    @Pattern(regexp = "^[0-9]*$", message = "Mobile number must contains only digits!")
    private String mobileNo;
}

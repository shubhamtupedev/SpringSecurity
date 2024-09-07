package com.example.springsecurity.entity;

import com.example.springsecurity.entityDTO.ApplicationUsersDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "APPLICATION_USERS", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL"), @UniqueConstraint(columnNames = "PHONE_NUMBER")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUsers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PRIMARY_EMAIL")
    private String primary_Email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "USER_PASSWORD")
    private String password;

    public ApplicationUsers(String email, String primary_Email, String phoneNumber, String password) {
        this.email = email;
        this.primary_Email = primary_Email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}

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

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "USER_PASSWORD")
    private String password;

    public ApplicationUsers(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}

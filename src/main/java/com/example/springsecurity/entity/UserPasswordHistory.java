package com.example.springsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYSTEM_USER_PASSWORD_HISTORY", uniqueConstraints = {@UniqueConstraint(columnNames = "USER_PWD")})
public class UserPasswordHistory extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UPH_ID")
    private Long id;

    @Column(name = "USER_ID")
    @NotNull
    private Long userId;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "USER_PWD")
    @NotNull
    private String password;

    public UserPasswordHistory(Long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}

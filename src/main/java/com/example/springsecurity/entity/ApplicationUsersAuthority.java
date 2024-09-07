package com.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "APPLICATION_USERS_AUTHORITY", uniqueConstraints = {@UniqueConstraint(columnNames = "EMAIL")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUsersAuthority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_TYPE")
    private String userType;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private ApplicationUsers applicationUsers;

    public ApplicationUsersAuthority(String userType, ApplicationUsers applicationUsers) {
        this.userType = userType;
        this.applicationUsers = applicationUsers;
    }
}

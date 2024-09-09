package com.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @OneToOne(fetch = FetchType.LAZY) // Set fetch type to LAZY
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    @ToString.Exclude // Exclude from Lombok's toString generation to avoid recursion
    private ApplicationUsers applicationUsers;

    public ApplicationUsersAuthority(String userType, ApplicationUsers applicationUsers) {
        this.userType = userType;
        this.applicationUsers = applicationUsers;
    }

    @Override
    public String toString() {
        return "ApplicationUsersAuthority{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                '}';
    }
}

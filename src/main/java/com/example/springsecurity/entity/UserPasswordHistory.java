package com.example.springsecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MST_USR_LST_PWD_HST")
public class UserPasswordHistory extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UPH_ID")
    private Long id;

    @Column(name = "USER_NAME")
    @NotBlank
    private String userName;

    @Column(name = "USER_ID")
    @NotBlank
    private Long userId;

    @Column(name = "USER_PWD")
    @NotBlank
    private String userPassword;

}

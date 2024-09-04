package com.example.springsecurity.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.example.springsecurity.entityDTO.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APPLICATION_USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "EMAIL"),
		@UniqueConstraint(columnNames = "PHONE_NUMBER") })
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "USER_PWD")
	private String password;

	@Column(name = "PWD_EXPIRY_DATE")
	private LocalDateTime passwordExpiryDate;

	@Column(name = "PWD_EXPIRY_ALERT_DATE")
	private LocalDateTime passwordExpiryAlertDate;

	@Column(name = "USER_TYPE")
	private String userType;

	public User(UserDTO userDTO) {
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.phoneNumber = userDTO.getPhoneNumber();
	}
}

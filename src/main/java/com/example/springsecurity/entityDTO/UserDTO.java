package com.example.springsecurity.entityDTO;

import com.example.springsecurity.Utility.BooleanToStringConverter;
import com.example.springsecurity.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String userName;

    @Size(min = 8, message = "The password must be at least 8 characters long.")
    @Size(max = 15, message = "The password must be at least 15 characters long.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@.#$%^&*])[a-zA-Z0-9!@.#$%^&*]{8,15}", message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character.")
    @NotBlank(message = "The password field is required. Please provide a password.")
    private String password;

    private Boolean isAcntEnabled;

    private Timestamp passwordExpiryDate;

    @Email(message = "The email is not in the correct format. Please enter a valid email.")
    @NotBlank(message = "The email is required. Please provide a email.")
    private String email;

    @NotBlank(message = "The mobile no is not in the correct format. Please enter a valid mobile no.")
    @Size(min = 10, max = 10, message = "The mobile no must be at least 10 characters long.")
    @Pattern(regexp = "^[0-9]*$", message = "The mobile number is invalid. It must be numeric and contain no special characters.")
    private String mobileNo;

    private boolean inactive;

    private Timestamp passwordExpiryAlertDate;

    @NotBlank(message = "The first name is required. Please provide a first name.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The first name is invalid. It must not contain digits and special characters.")
    private String firstName;

    @NotBlank(message = "The last name is required. Please provide a last name.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The last name is invalid. It must not contain digits and special characters.")
    private String lastName;

    private String userType;

    @NotNull(message = "date of birth is required!")
    private Date dateOfBirth;

    private Long maximumSessions;

    private Long currentSessions;

    private Timestamp lastLoginDate;

    private boolean onlineInd;

    private Long graceLongRemaining;

    private Timestamp lastLogoutDate;

    private String otp;

    private Timestamp otpValidUntil;

}

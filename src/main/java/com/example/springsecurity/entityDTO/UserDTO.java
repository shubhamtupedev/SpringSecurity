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

    @NotBlank(message = "The email is required. Please provide a email.")
    @Email(message = "The email is not in the correct format. Please enter a valid email.")
    private String email;

    @NotBlank(message = "The phone number is required. Please provide a phone number.")
    @Size(min = 10, max = 10, message = "The phone number must be at least 10 characters long.")
    @Pattern(regexp = "^[0-9]*$", message = "The phone number is invalid. It must be numeric and contain no special characters.")
    private String phoneNumber;

    @NotBlank(message = "The password field is required. Please provide a password.")
    private String password;

}

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
    @Size(min = 8, message = "The password must be at least 8 characters long.")
    @Size(max = 15, message = "The password must be at least 15 characters long.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@.#$%^&*])[a-zA-Z0-9!@.#$%^&*]{8,15}", message = "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character.")
    private String password;

}

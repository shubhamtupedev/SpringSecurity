package com.example.springsecurity.apiDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @Email(message = "The email is not in the correct format. Please enter a valid email.")
    @NotBlank(message = "The email field is required. please provide a email.")
    private String email;

    @NotBlank(message = "The password field is required, please provide password.")
    private String password;
}

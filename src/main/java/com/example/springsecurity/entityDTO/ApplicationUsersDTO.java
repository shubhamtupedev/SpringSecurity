package com.example.springsecurity.entityDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUsersDTO {

    Long id;

    @Email(message = "The email is not in the correct format. Please enter a valid email.")
    @NotBlank(message = "The email field is required. please provide a email.")
    private String email;

    @Pattern(regexp = "^[0-9]*$", message = "The phone number is not in the correct format. It must be numeric and contain no special characters.")
    @NotBlank(message = "The phone number field is required. Please provide phone number.")
    @Size(max = 10, min = 10, message = "The phone number must be at least 10 characters long.")
    private String phoneNumber;

    @NotBlank(message = "The password field is required, please provide password.")
    private String password;
}

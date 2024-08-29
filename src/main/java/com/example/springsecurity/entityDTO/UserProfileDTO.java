package com.example.springsecurity.entityDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class UserProfileDTO {
    @NotBlank(message = "The first name is required. Please provide a first name.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The first name is invalid. It must not contain digits and special characters.")
    private String firstName;

    @NotBlank(message = "The last name is required. Please provide a last name.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The last name is invalid. It must not contain digits and special characters.")
    private String lastName;

    @NotNull(message = "date of birth is required!")
    private LocalDateTime dateOfBirth;
}

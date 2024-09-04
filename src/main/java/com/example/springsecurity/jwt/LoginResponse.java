package com.example.springsecurity.jwt;

import com.example.springsecurity.entityDTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String jwtToken;
    private String email;
    private UserDTO userDTO;
    private String message;
}

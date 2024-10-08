package com.example.springsecurity.controller;

import com.example.springsecurity.apiDTO.LoginRequestDTO;
import com.example.springsecurity.applicationenum.ApiApplicationStatus;
import com.example.springsecurity.entity.ApplicationUsers;
import com.example.springsecurity.entity.ApplicationUsersAuthority;
import com.example.springsecurity.entityDTO.ApplicationUsersAuthorityDTO;
import com.example.springsecurity.entityDTO.ApplicationUsersDTO;
import com.example.springsecurity.exceptions.ApplicationServiceException;
import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.jwt.JWTUtils;
import com.example.springsecurity.responseDTO.ApiResponseDTO;
import com.example.springsecurity.service.ApplicationUsersService;
import com.example.springsecurity.utility.EntityMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ApplicationUsersController {

    @Autowired
    private JWTUtils jwtUtil;

    private ApplicationUsersService applicationUsersService;

    public ApplicationUsersController(ApplicationUsersService applicationUsersService) {
        this.applicationUsersService = applicationUsersService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<ApiResponseDTO<?>> saveUser(@RequestBody @Valid ApplicationUsersDTO applicationUsersDTO) throws ApplicationServiceException, ValidationException {
        return applicationUsersService.saveUser(applicationUsersDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponseDTO<?>> loginUser(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws ApplicationServiceException, ValidationException {
        try {
            String decodedPassword = new String(Base64.getDecoder().decode(loginRequestDTO.getPassword()));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), decodedPassword));

            String token = jwtUtil.generateTokenFromUsername(loginRequestDTO.getEmail());
            HashMap map = new HashMap();
            map.put("status", "success");
            map.put("message", "User logged in successfully.");
            map.put("token", token);
            map.put("principal", authentication.getPrincipal().toString());
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(ApiApplicationStatus.SUCCESS.name(), map));
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), exception.getMessage()));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiApplicationStatus.FAIL.name(), exception.getMessage()));
        }
    }


    @GetMapping("/userDetails")
    public ResponseEntity<ApiResponseDTO<?>> getUserDetail() throws ApplicationServiceException {
        List reponseList = new ArrayList<>();
        List<ApplicationUsers> applicationUsers = applicationUsersService.getUserDetails();
        Iterator iterator = applicationUsers.iterator();
        while (iterator.hasNext()) {
            ApplicationUsers applicationUsers1 = (ApplicationUsers) iterator.next();
            System.out.println("applicationUsers" + applicationUsers1);
            ApplicationUsersDTO applicationUsersDTO = EntityMapper.mapToDto(applicationUsers1, ApplicationUsersDTO.class);
//            ApplicationUsersAuthority applicationUsersAuthority = applicationUsers1.getApplicationUsersAuthority();
//            ApplicationUsersAuthorityDTO applicationUsersAuthorityDTO = EntityMapper.mapToDto(applicationUsersAuthority, ApplicationUsersAuthorityDTO.class);
//            System.out.println("applicationUsersAuthorityDTO" + applicationUsers1.getApplicationUsersAuthority());
//            applicationUsersDTO.setApplicationUsersAuthorityDTO(applicationUsersAuthorityDTO);
//            System.out.println("applicationUsersAuthorityDTO" + applicationUsersDTO);
            reponseList.add(applicationUsersDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>(ApiApplicationStatus.SUCCESS.name(), reponseList));
    }

}

package com.example.springsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getHelloWorld")
    public String getHelloWorld() {
        return "Hello World !";
    }
}

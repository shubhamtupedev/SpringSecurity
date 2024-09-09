package com.example.springsecurity.exceptions.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void handleException(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        // Set the response status and content type
        response.setStatus(status.value());
        response.setContentType("application/json");

        // Create a response body as a map
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", message);

        // Convert the map to a JSON string and write it to the response
        String jsonResponse = objectMapper.writeValueAsString(responseBody);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
}

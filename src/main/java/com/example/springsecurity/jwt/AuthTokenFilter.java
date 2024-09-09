package com.example.springsecurity.jwt;

import com.example.springsecurity.exceptions.ValidationException;
import com.example.springsecurity.exceptions.handler.JwtExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;


public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    private final JwtExceptionHandler exceptionHandler = new JwtExceptionHandler();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!request.getServletPath().equals("/api/v1/auth/register") && !request.getServletPath().equals("/api/v1/auth/login")) {
                String jwt = parseJwtToken(request);
                if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ValidationException e) {
            exceptionHandler.handleException(response, e.getMessage(), HttpStatus.BAD_REQUEST);
            return;
        } catch (ExpiredJwtException e) {
            exceptionHandler.handleException(response, "Error: JWT Token has expired.", HttpStatus.UNAUTHORIZED);
            return;
        } catch (MalformedJwtException e) {
            exceptionHandler.handleException(response, "Error: Malformed JWT Token.", HttpStatus.BAD_REQUEST);
            return;
        } catch (Exception e) {
            exceptionHandler.handleException(response, "Error: An internal server error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwtToken(HttpServletRequest request) throws ValidationException {
        String jwt = jwtUtils.getJwtFromHeader(request);
        if (jwt == null) {
            throw new ValidationException("Please provide JWT token");
        }
        return jwt;
    }
}

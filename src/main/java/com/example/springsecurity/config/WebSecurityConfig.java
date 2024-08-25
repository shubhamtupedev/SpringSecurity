package com.example.springsecurity.config;

import com.example.springsecurity.jwt.AuthEntryPointJwt;
import com.example.springsecurity.jwt.AuthTokenFilter;
import com.example.springsecurity.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {


    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private JwtUtils jwtUtils;


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/api/v1/user/login").permitAll().requestMatchers("/api/v1/user/registerUser").permitAll().anyRequest().authenticated());
//        http.formLogin(Customizer.withDefaults()).userDetailsService(userDetailsService).pa
        http.csrf((csrf) -> csrf.disable());
        http.cors(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement((manager) -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.authenticationProvider(daoAuthenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


//    @Bean
//    UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("admin").password("{noop}admin@1234").roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("user").password("{noop}user@123").roles("STANDARD").build();
//        return new InMemoryUserDetailsManager(user1, user2);
//    }


//    @Bean
//    UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.withUsername("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("user").password(passwordEncoder().encode("user@123")).roles("STANDARD").build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user1);
//        jdbcUserDetailsManager.createUser(user2);
//
//        jdbcUserDetailsManager.getUsersByUsernameQuery();
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.withUsername("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("user").password(passwordEncoder().encode("user@123")).roles("STANDARD").build();
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user1);
//        jdbcUserDetailsManager.createUser(user2);
//
//        return jdbcUserDetailsManager;
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {

//        authConfig.

        return authConfig.getAuthenticationManager();
    }

//    private AuthenticationProvider daoAuthenticationProvider() throws Exception {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }

}

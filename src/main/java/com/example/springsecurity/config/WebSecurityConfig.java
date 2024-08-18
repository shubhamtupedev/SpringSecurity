//package com.example.springsecurity.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
////@EnableWebSecurity
////@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/api/v1/user/**").permitAll().anyRequest().authenticated());
////        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//
////    @Bean
////    UserDetailsService userDetailsService() {
////        UserDetails user1 = User.withUsername("admin").password("{noop}admin@1234").roles("ADMIN").build();
////        UserDetails user2 = User.withUsername("user").password("{noop}user@123").roles("STANDARD").build();
////        return new InMemoryUserDetailsManager(user1, user2);
////    }
//
//
////    @Bean
////    UserDetailsService userDetailsService() {
////
////        UserDetails user1 = User.withUsername("admin").password(passwordEncoder().encode("admin@123")).roles("ADMIN").build();
////        UserDetails user2 = User.withUsername("user").password(passwordEncoder().encode("user@123")).roles("STANDARD").build();
////
////        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
////        jdbcUserDetailsManager.createUser(user1);
////        jdbcUserDetailsManager.createUser(user2);
////
////        jdbcUserDetailsManager.getUsersByUsernameQuery();
////        return jdbcUserDetailsManager;
////    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
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
//
//}

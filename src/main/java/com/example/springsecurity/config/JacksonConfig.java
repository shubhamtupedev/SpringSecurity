//package com.example.springsecurity.config;
//
//import com.fasterxml.jackson.core.StreamWriteConstraints;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.getFactory().configure(StreamWriteConstraints., 2000); // Increase depth here
//        return mapper;
//    }
//}

package com.example.springsecurity.entityDTO;

import com.example.springsecurity.entity.ApplicationUsers;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

public class ApplicationUsersAuthorityDTO {
    private Long id;
    private String userType;

    private ApplicationUsersDTO applicationUsersDTO;

    @Override
    public String toString() {
        return "ApplicationUsersAuthorityDTO{" +
                "id=" + id +
                ", userType='" + userType + '\'' +
                '}';
    }
}

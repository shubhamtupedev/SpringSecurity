package com.example.springsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditableModel {

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate;

    @Column(name = "DTL_ID")
    private Long dtlId;

}

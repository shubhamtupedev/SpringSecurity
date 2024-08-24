package com.example.springsecurity.repository;

import com.example.springsecurity.entity.SystemParameter;
import com.example.springsecurity.entityDTO.SystemParameterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long> {
    public List<SystemParameter> findBySysParamKey(String sysParamKey);

    @Query("SELECT sysParamVal FROM SystemParameter WHERE sysParamKey = ?1")
    public String getSysParamValue(String sysParamKey);
}

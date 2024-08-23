package com.example.springsecurity.repository;

import com.example.springsecurity.entity.SystemParameter;
import com.example.springsecurity.entityDTO.SystemParameterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long> {

    public List<SystemParameterDTO> findBySysParamKey(String sysParamKey);
}

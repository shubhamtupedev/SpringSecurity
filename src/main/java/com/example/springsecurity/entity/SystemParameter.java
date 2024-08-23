package com.example.springsecurity.entity;

import com.example.springsecurity.Utility.BooleanToStringConverter;
import com.example.springsecurity.entityDTO.SystemParameterDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MST_SYS_PARAM", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SYS_PARAM_KEY")
})
public class SystemParameter extends AuditableModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SP_ID")
    private Long id;

    @Column(name = "SYS_PARAM_KEY")
    @NotBlank
    private String sysParamKey;

    @Column(name = "SYS_PARAM_VALUE")
    @NotBlank
    private String sysParamVal;

    @Column(name = "SYS_PARAM_DESC")
    @NotBlank
    private String sysParamDesc;

    @Column(name = "INACTIVE")
    @Convert(converter = BooleanToStringConverter.class)
    private boolean inactive;

    public SystemParameter(SystemParameterDTO systemParameterDTO) {
        this.sysParamKey = systemParameterDTO.getSysParamKey();
        this.sysParamVal = systemParameterDTO.getSysParamVal();
        this.sysParamDesc = systemParameterDTO.getSysParamDesc();
        this.inactive = systemParameterDTO.isInactive();
    }
}

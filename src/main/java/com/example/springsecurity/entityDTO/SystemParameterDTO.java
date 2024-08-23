package com.example.springsecurity.entityDTO;

import com.example.springsecurity.Utility.BooleanToStringConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemParameterDTO {

    private Long id;

    @NotBlank(message = "The system parameter code is required. Please provide a system parameter code.")
    private String sysParamKey;

    @NotBlank(message = "The system parameter value is required. Please provide a system parameter value.")
    private String sysParamVal;

    @NotBlank(message = "The system parameter description is required. Please provide a system parameter description.")
    private String sysParamDesc;

    private boolean inactive;

}

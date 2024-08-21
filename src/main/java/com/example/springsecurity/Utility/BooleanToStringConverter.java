package com.example.springsecurity.Utility;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    enum YesNoFlag {
        Y, N
    }

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? YesNoFlag.Y.toString() : YesNoFlag.N.toString();
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return YesNoFlag.Y.toString().equals(dbData);
    }
}

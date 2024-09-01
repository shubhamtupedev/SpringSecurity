package com.example.springsecurity.Utility;

import org.springframework.beans.BeanUtils;

public class EntityMapper {
    /**
     * Copies properties from a source entity to a target DTO.
     *
     * @param source      The source entity object.
     * @param targetClass The class type of the target DTO.
     * @param <S>         The source entity type.
     * @param <T>         The target DTO type.
     * @return An instance of the target DTO with copied properties.
     */
    public static <S, T> T mapToDto(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping to DTO: " + e.getMessage(), e);
        }
    }

    /**
     * Copies properties from a source DTO to a target entity.
     *
     * @param source      The source DTO object.
     * @param targetClass The class type of the target entity.
     * @param <S>         The source DTO type.
     * @param <T>         The target entity type.
     * @return An instance of the target entity with copied properties.
     */
    public static <S, T> T mapToEntity(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping to Entity: " + e.getMessage(), e);
        }
    }
}

package com.silchenko.arsen.testpumbproject.mapper;

import com.silchenko.arsen.testpumbproject.dto.AnimalDto;
import com.silchenko.arsen.testpumbproject.model.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    AnimalDto toDto(Animal animal);
}

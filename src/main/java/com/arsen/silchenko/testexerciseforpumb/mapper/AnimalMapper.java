package com.arsen.silchenko.testexerciseforpumb.mapper;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalCSV;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalXML;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDto toDto(Animal animal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "type", expression = "java(Animal.Type.valueOf(animalCSVRepresentation.getType().toUpperCase()))")
    @Mapping(target = "sex", expression = "java(Animal.Sex.valueOf(animalCSVRepresentation.getSex().toUpperCase()))")
    Animal toModel(AnimalCSV animalCSVRepresentation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "type", expression = "java(Animal.Type.valueOf(animalCSVRepresentation.getType().toUpperCase()))")
    @Mapping(target = "sex", expression = "java(Animal.Sex.valueOf(animalCSVRepresentation.getSex().toUpperCase()))")
    Animal toModel(AnimalXML animalCSVRepresentation);
}

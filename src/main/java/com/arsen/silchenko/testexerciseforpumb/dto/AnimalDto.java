package com.arsen.silchenko.testexerciseforpumb.dto;

import com.arsen.silchenko.testexerciseforpumb.model.Animal;

public record AnimalDto(String name,
                        Animal.Type type,
                        Animal.Sex sex,
                        Integer weight,
                        Integer cost,
                        Animal.Category category) { }

package com.silchenko.arsen.testpumbproject.dto;

import com.silchenko.arsen.testpumbproject.model.Animal;

public record AnimalDto(String name,
                        String type,

                        String gender,
                        Integer weight,
                        Integer cost,
                        Animal.Category category) { }

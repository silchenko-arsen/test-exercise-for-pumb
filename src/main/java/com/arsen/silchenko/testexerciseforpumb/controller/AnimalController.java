package com.arsen.silchenko.testexerciseforpumb.controller;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.dto.AnimalSearchParametersDto;
import com.arsen.silchenko.testexerciseforpumb.service.AnimalService;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/animals")
@Tag(name = "Animal management", description = "Endpoints for managing animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/search")
    @Operation(summary = "Search animals by parameters",
            description = "Get a list of all animals suitable for the parameters")
    public List<AnimalDto> searchAnimals(AnimalSearchParametersDto searchParameters, Sort sort) {
        return animalService.search(searchParameters, sort);
    }
}





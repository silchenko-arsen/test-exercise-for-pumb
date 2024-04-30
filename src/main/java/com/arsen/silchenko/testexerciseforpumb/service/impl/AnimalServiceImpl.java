package com.arsen.silchenko.testexerciseforpumb.service.impl;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.dto.AnimalSearchParametersDto;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.service.AnimalService;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal.AnimalSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final AnimalSpecificationBuilder animalSpecificationBuilder;

    @Override
    public List<AnimalDto> search(AnimalSearchParametersDto searchParameters, Sort sort) {
        Specification<Animal> animalSpecification = animalSpecificationBuilder.build(searchParameters);
        return animalRepository.findAll(animalSpecification, sort)
                .stream()
                .map(animalMapper::toDto).toList();
    }
}

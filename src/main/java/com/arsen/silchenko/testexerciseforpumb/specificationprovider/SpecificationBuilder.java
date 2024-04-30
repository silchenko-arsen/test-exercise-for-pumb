package com.arsen.silchenko.testexerciseforpumb.specificationprovider;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(AnimalSearchParametersDto animalSearchParametersDto);
}

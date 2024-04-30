package com.arsen.silchenko.testexerciseforpumb.service;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.dto.AnimalSearchParametersDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AnimalService {
    List<AnimalDto> search(AnimalSearchParametersDto searchParameters, Sort sort);
}

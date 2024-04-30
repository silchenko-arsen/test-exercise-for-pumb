package com.arsen.silchenko.testexerciseforpumb.service;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.dto.AnimalSearchParametersDto;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.service.impl.AnimalServiceImpl;
import com.arsen.silchenko.testexerciseforpumb.specificationprovider.animal.AnimalSpecificationBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AnimalMapper animalMapper;

    @Mock
    private AnimalSpecificationBuilder animalSpecificationBuilder;

    @InjectMocks
    private AnimalServiceImpl animalService;


    @Test
    public void testSearchWithEmptyResult() {
        AnimalSearchParametersDto searchParameters = new AnimalSearchParametersDto(new String[]{}, new String[]{}, new String[]{});
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        when(animalRepository.findAll((Specification<Animal>) null, sort)).thenReturn(Collections.emptyList());

        List<AnimalDto> result = animalService.search(searchParameters, sort);

        assertTrue(result.isEmpty());
        verify(animalRepository).findAll((Specification<Animal>) null, sort);
    }

    @Test
    public void testSearchWithResults() {
        Animal cat = new Animal();
        cat.setName("Kitty");
        cat.setType(Animal.Type.CAT);

        Animal dog = new Animal();
        dog.setName("Doggy");
        dog.setType(Animal.Type.DOG);

        List<Animal> animals = Arrays.asList(cat, dog);

        AnimalDto catDto = new AnimalDto("Kitty", Animal.Type.CAT, null, null, null, null);
        AnimalDto dogDto = new AnimalDto("Doggy", Animal.Type.DOG, null, null, null, null);

        AnimalSearchParametersDto searchParameters = new AnimalSearchParametersDto(new String[]{}, new String[]{}, new String[]{});
        Sort sort = Sort.by(Sort.Direction.ASC, "name");

        when(animalSpecificationBuilder.build(searchParameters)).thenReturn(null);
        when(animalRepository.findAll((Specification<Animal>) null, sort)).thenReturn(animals);
        when(animalMapper.toDto(cat)).thenReturn(catDto);
        when(animalMapper.toDto(dog)).thenReturn(dogDto);

        List<AnimalDto> result = animalService.search(searchParameters, sort);

        assertEquals(2, result.size());
        assertTrue(result.contains(catDto));
        assertTrue(result.contains(dogDto));
        verify(animalRepository).findAll((Specification<Animal>) null, sort);
        verify(animalMapper, times(2)).toDto(any(Animal.class));
    }

}


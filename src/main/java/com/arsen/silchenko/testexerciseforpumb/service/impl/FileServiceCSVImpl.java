package com.arsen.silchenko.testexerciseforpumb.service.impl;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalCSV;
import com.arsen.silchenko.testexerciseforpumb.service.FileService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceCSVImpl implements FileService {
    private final AnimalMapper animalMapper;
    private final AnimalRepository animalRepository;

    @Override
    public List<AnimalDto> upload(MultipartFile file) {
        List<AnimalCSV> animalCSVs;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            animalCSVs = new CsvToBeanBuilder<AnimalCSV>(reader)
                    .withType(AnimalCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();
        }  catch (IOException e) {
            throw new CsvRuntimeException("Error reading file: " + file.getOriginalFilename(), e);
        }
        List<Animal> animals = animalCSVs.stream()
                .filter(this::isValidAnimal)
                .map(animalMapper::toModel)
                .map(Animal::setCategory)
                .toList();
        return animalRepository.saveAll(animals)
                .stream()
                .map(animalMapper::toDto).toList();
    }

    private boolean isValidAnimal(AnimalCSV animal) {
        return animal.getName() != null && !animal.getName().isEmpty() &&
                animal.getType() != null && !animal.getType().isEmpty() &&
                animal.getSex() != null && !animal.getSex().isEmpty() &&
                animal.getWeight() != null && animal.getWeight() >= 0 &&
                animal.getCost() != null && animal.getCost() >= 0;
    }
}

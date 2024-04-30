package com.arsen.silchenko.testexerciseforpumb.service.impl;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.exception.XmlParsingException;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalCSV;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalXML;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalsXML;
import com.arsen.silchenko.testexerciseforpumb.service.FileService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServiceXMLImpl implements FileService {
    private final AnimalMapper animalMapper;
    private final AnimalRepository animalRepository;

    public FileServiceXMLImpl(AnimalMapper animalMapper, AnimalRepository animalRepository) {
        this.animalMapper = animalMapper;
        this.animalRepository = animalRepository;
    }

    @Override
    public List<AnimalDto> upload(MultipartFile file) {
        AnimalsXML animalsXML;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(AnimalsXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            animalsXML = (AnimalsXML) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new XmlParsingException("Error parsing XML: " + file.getOriginalFilename(), e);
        } catch (IOException e) {
            throw new XmlParsingException("Error reading file: " + file.getOriginalFilename(), e);
        }

        List<Animal> animals = animalsXML.getAnimalXMLList().stream()
                .filter(this::isValidAnimal)
                .map(animalMapper::toModel)
                .map(Animal::setCategory)
                .toList();
        return animalRepository.saveAll(animals)
                .stream()
                .map(animalMapper::toDto).toList();
    }

    private boolean isValidAnimal(AnimalXML animal) {
        return animal.getName() != null && !animal.getName().isEmpty() &&
                animal.getType() != null && !animal.getType().isEmpty() &&
                animal.getSex() != null && !animal.getSex().isEmpty() &&
                animal.getWeight() != null && animal.getWeight() >= 0 &&
                animal.getCost() != null && animal.getCost() >= 0;
    }
}

package com.arsen.silchenko.testexerciseforpumb.service;


import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalCSV;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalXML;
import com.arsen.silchenko.testexerciseforpumb.service.impl.FileServiceXMLImpl;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileServiceXMLImplTest {

    @Mock
    private AnimalMapper animalMapper;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private FileServiceXMLImpl fileService;

    @Test
    void testUploadValidData() {
        String xmlContent = """
                <?xml version="1.0" encoding="UTF-8"?>
                <animals>
                    <animal>
                        <name>Buddy</name>
                        <type>cat</type>
                        <sex>female</sex>
                        <weight>41</weight>
                        <cost>78</cost>
                    </animal>
                    <animal>
                        <name>Duke</name>
                        <type>cat</type>
                        <sex>male</sex>
                        <weight>33</weight>
                        <cost>108</cost>
                    </animal>
                </animals>""";
        MultipartFile file = new MockMultipartFile("file", "file.xml", "application/xml", xmlContent.getBytes());
        when(animalMapper.toModel((AnimalXML) any())).thenAnswer(invocation -> {
            AnimalXML animalXML = invocation.getArgument(0);
            return new Animal(animalXML.getName(), Animal.Type.valueOf(animalXML.getType().toUpperCase()),
                    Animal.Sex.valueOf(animalXML.getSex().toUpperCase()), animalXML.getWeight(), animalXML.getCost());
        });
        when(animalMapper.toDto(any())).thenAnswer(invocation -> {
            Animal animal = invocation.getArgument(0);
            return new AnimalDto(animal.getName(), animal.getType(), animal.getSex(), animal.getWeight(), animal.getCost(), Animal.Category.FOURTH);
        });
        when(animalRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));
        List<AnimalDto> result = fileService.upload(file);
        assertEquals(2, result.size());
        verify(animalRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testUploadInvalidData() {
        String xmlContent = """
                <?xml version="1.0" encoding="UTF-8"?>
                <animals>
                    <animal>
                        <name>Buddy</name>
                        <sex>female</sex>
                        <weight>41</weight>
                        <cost>78</cost>
                    </animal>
                    <animal>
                        <name>Duke</name>
                        <type>cat</type>
                        <sex>male</sex>
                        <weight>33</weight>
                        <cost>-108</cost>
                    </animal>
                </animals>""";
        MultipartFile file = new MockMultipartFile("file", "file.xml", "application/xml", xmlContent.getBytes());

        List<AnimalDto> result = fileService.upload(file);

        assertEquals(0, result.size());
        verifyNoInteractions(animalMapper);
        verify(animalRepository, times(1)).saveAll(anyList());
    }
}

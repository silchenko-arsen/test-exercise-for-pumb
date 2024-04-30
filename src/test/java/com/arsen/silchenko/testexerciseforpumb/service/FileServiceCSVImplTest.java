package com.arsen.silchenko.testexerciseforpumb.service;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalCSV;
import com.arsen.silchenko.testexerciseforpumb.service.impl.FileServiceCSVImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceCSVImplTest {

    @Mock
    private AnimalMapper animalMapper;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private FileServiceCSVImpl fileService;

    @Test
    void testUploadValidData() {
        String csvContent = """
                Name,Type,Sex,Weight,Cost
                Buddy,cat,female,41,78
                Duke,cat,male,33,108
                """;
        MultipartFile file = new MockMultipartFile("file", "file.csv", "text/csv", csvContent.getBytes());
        when(animalMapper.toModel((AnimalCSV) any())).thenAnswer(invocation -> {
            AnimalCSV animalCSV = invocation.getArgument(0);
            return new Animal(animalCSV.getName(), Animal.Type.valueOf(animalCSV.getType().toUpperCase()),
                    Animal.Sex.valueOf(animalCSV.getSex().toUpperCase()), animalCSV.getWeight(), animalCSV.getCost());
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
    public void testUploadInvalidData() {
        String csvContent = """
                Name,Type,Sex,Weight,Cost
                Buddy,,female,41,78
                Duke,cat,male,33,-108
                """;
        MockMultipartFile file = new MockMultipartFile("file", "file.csv", "text/csv", csvContent.getBytes());
        List<AnimalDto> result = fileService.upload(file);
        assertEquals(0, result.size());
        verifyNoInteractions(animalMapper);
        verify(animalRepository, times(1)).saveAll(anyList());
    }
}


package com.arsen.silchenko.testexerciseforpumb.service.impl;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.mapper.AnimalMapper;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.arsen.silchenko.testexerciseforpumb.repository.AnimalRepository;
import com.arsen.silchenko.testexerciseforpumb.represantation.AnimalCSV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//public class FileServiceCSVImplTest {
//
//    @Mock
//    private AnimalMapper animalMapper;
//
//    @Mock
//    private AnimalRepository animalRepository;
//
//    @InjectMocks
//    private FileServiceCSVImpl fileService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testUpload_ValidData() throws IOException {
//        // Mocking input file
//        String csvContent = "Name,Type,Sex,Weight,Cost\n" +
//                "Buddy,cat,female,41,78\n" +
//                "Duke,cat,male,33,108\n";
//        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
//        MockMultipartFile file = new MockMultipartFile("file", "animals.csv", "text/csv", inputStream);
//
//        // Mocking behavior of AnimalMapper
//        AnimalCSV buddyCsv = new AnimalCSV("Buddy", "cat", "female", 41, 78);
//        AnimalCSV dukeCsv = new AnimalCSV("Duke", "cat", "male", 33, 108);
//        Animal buddy = new Animal("Buddy", Animal.Type.CAT, Animal.Sex.FEMALE, 41, 78);
//        Animal duke = new Animal("Duke", Animal.Type.CAT, Animal.Sex.FEMALE, 33, 108);
//        when(animalMapper.toModel(buddyCsv)).thenReturn(buddy);
//        when(animalMapper.toModel(dukeCsv)).thenReturn(duke);
//        when(animalMapper.toDto(buddy)).thenReturn(new AnimalDto("Buddy", Animal.Type.CAT, Animal.Sex.FEMALE, 41, 78, Animal.Category.FOURTH));
//        when(animalMapper.toDto(duke)).thenReturn(new AnimalDto("Duke", Animal.Type.CAT, Animal.Sex.MALE, 33, 108, Animal.Category.FOURTH));
//
//        when(animalRepository.saveAll(Arrays.asList(buddy, duke))).thenReturn(Arrays.asList(buddy, duke));
//
//        // Call the method under test
//        List<AnimalDto> result = fileService.upload(file);
//
//        // Verify interactions and assertions
//        assertEquals(2, result.size());
//        verify(animalRepository, times(1)).saveAll(Arrays.asList(buddy, duke));
//    }
//
//    @Test
//    public void testUpload_InvalidData() throws IOException {
//        // Mocking input file with invalid data
//        String csvContent = "Name,Type,Sex,Weight,Cost\n" +
//                "Buddy,,female,41,78\n" +  // Missing 'Type'
//                "Duke,cat,male,33,-108\n";  // Negative 'Cost'
//        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
//        MockMultipartFile file = new MockMultipartFile("file", "animals.csv", "text/csv", inputStream);
//
//        // Call the method under test
//        List<AnimalDto> result = fileService.upload(file);
//
//        // Verify interactions and assertions
//        assertEquals(0, result.size()); // Since both entries are invalid
//        verifyNoInteractions(animalMapper); // No interactions with AnimalMapper
//        verifyNoInteractions(animalRepository); // No interactions with AnimalRepository
//    }
//
//    // Add more test cases as needed
//}

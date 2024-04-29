package com.arsen.silchenko.testexerciseforpumb.service;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileReaderService {
    List<AnimalDto> upload(MultipartFile file);
}

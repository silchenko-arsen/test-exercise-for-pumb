package com.arsen.silchenko.testexerciseforpumb.service;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileService {
    List<AnimalDto> upload(MultipartFile fileName);
}

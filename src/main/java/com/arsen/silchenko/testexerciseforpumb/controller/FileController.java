package com.arsen.silchenko.testexerciseforpumb.controller;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.strategy.FileStrategy;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/files")
@Tag(name = "File management", description = "Endpoints for managing files")
@RequiredArgsConstructor
public class FileController {
    private final FileStrategy fileStrategy;

    @PostMapping("/uploads")
    @Operation(summary = "Get a list of animals", description = "Get a list of animals by various filters and sorting")
    public List<AnimalDto> uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStrategy.getFileServiceByFile(file.getContentType()).upload(file);
    }
}

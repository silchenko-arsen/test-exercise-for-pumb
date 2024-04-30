package com.arsen.silchenko.testexerciseforpumb.strategy;

import com.arsen.silchenko.testexerciseforpumb.service.FileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileStrategy {
    private static final String CSV = "text/csv";
    private static final String XML = "application/xml";
    private final Map<String, FileService> serviceMap = new HashMap<>();

    private final FileService fileServiceCSV;
    private final FileService fileServiceXML;

    @Autowired
    public FileStrategy(@Qualifier("fileServiceCSVImpl") FileService fileServiceCSV, @Qualifier("fileServiceXMLImpl") FileService fileServiceXML) {
        this.fileServiceCSV = fileServiceCSV;
        this.fileServiceXML = fileServiceXML;
    }

    @PostConstruct
    public void initMap() {
        serviceMap.put(CSV, fileServiceCSV);
        serviceMap.put(XML, fileServiceXML);
    }
    public FileService getFileServiceByFile(String fileType) {
        return serviceMap.get(fileType);
    }
}

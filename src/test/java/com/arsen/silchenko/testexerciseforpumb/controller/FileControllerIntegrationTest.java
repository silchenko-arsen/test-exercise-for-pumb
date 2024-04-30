package com.arsen.silchenko.testexerciseforpumb.controller;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileControllerIntegrationTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @AfterAll
    static void afterAll(@Autowired DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/delete-for-animal-tests.sql")
            );
        }
    }

    @Test
    public void testUploadFileWithCsvData() throws Exception {
        String csvData = "Name,Type,Sex,Weight,Cost\n" +
                "Buddy,cat,female,41,78\n" +
                "Cooper,,female,46,23\n" +
                "Duke,cat,male,33,108\n" +
                "Rocky,dog,,18,77\n" +
                "Sadie,cat,male,26,27\n" +
                "Leo,cat,female,23,82\n" +
                ",cat,male,32,44\n" +
                "Lola,dog,male,35,105\n" +
                "Bailey,dog,male,42,46\n" +
                "Loki,cat,female,11,87";
        MockMultipartFile multipartFile = new MockMultipartFile("file", "file.csv", "text/csv", csvData.getBytes());
        MvcResult mvcResult = mockMvc.perform(multipart("/api/files/uploads")
                        .file(multipartFile))
                .andExpect(status().isOk())
                .andReturn();
        AnimalDto[] actual = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), AnimalDto[].class);
        assertEquals(7, actual.length);
    }

    @Test
    public void testUploadFileWithXmlData() throws Exception {
        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<animals>\n" +
                "    <animal>\n" +
                "        <name>Milo</name>\n" +
                "        <type>cat</type>\n" +
                "        <sex>male</sex>\n" +
                "        <weight>40</weight>\n" +
                "        <cost>51</cost>\n" +
                "    </animal>\n" +
                "    <animal>\n" +
                "        <name>Simon</name>\n" +
                "        <sex>male</sex>\n" +
                "        <weight>45</weight>\n" +
                "        <cost>17</cost>\n" +
                "    </animal>\n" +
                "</animals>";
        MockMultipartFile file = new MockMultipartFile("file", "file.xml", "application/xml", xmlData.getBytes());
        MvcResult mvcResult = mockMvc.perform(multipart("/api/files/uploads")
                        .file(file))
                .andExpect(status().isOk())
                .andReturn();
        AnimalDto[] actual = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), AnimalDto[].class);
        assertEquals(1, actual.length);
    }
}

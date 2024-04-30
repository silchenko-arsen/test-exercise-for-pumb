package com.arsen.silchenko.testexerciseforpumb.controller;

import com.arsen.silchenko.testexerciseforpumb.dto.AnimalDto;
import com.arsen.silchenko.testexerciseforpumb.model.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimalControllerIntegrationTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired DataSource dataSource,
                          @Autowired WebApplicationContext applicationContext) throws SQLException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/add-for-animal-tests.sql")
            );
        }
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
    void testSearchWithoutParameters() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/animals/search"))
                .andExpect(status().isOk())
                .andReturn();
        AnimalDto[] actual = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), AnimalDto[].class);
        assertEquals(7, actual.length);
    }


    @Test
    void testSearchWithTypeSexCategoryParametersAndSortAsc() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        get("/api/animals/search")
                                .param("types", "CAT")
                                .param("sexes", "FEMALE")
                                .param("categories", "FOURTH")
                                .param("sort", "name,asc"))
                .andExpect(status().isOk())
                .andReturn();
        AnimalDto[] actual = objectMapper.readValue(mvcResult
                .getResponse().getContentAsString(), AnimalDto[].class);
        AnimalDto[] expected = new AnimalDto[]{
                new AnimalDto("Buddy", Animal.Type.CAT, Animal.Sex.FEMALE,41,78, Animal.Category.FOURTH),
                new AnimalDto("Loki", Animal.Type.CAT, Animal.Sex.FEMALE,11,87, Animal.Category.FOURTH),
        };
        assertEquals(expected.length, actual.length);
        assertEquals(expected[0].name(), actual[0].name());
    }
}

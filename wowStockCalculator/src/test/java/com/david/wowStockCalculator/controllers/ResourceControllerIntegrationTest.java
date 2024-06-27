package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ResourceControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public ResourceControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    public void testThatCreateResourceSuccessfullyReturnsHttp201Created() throws Exception {
        Resource resource =TestDataUtil.createTestResourceA();
        resource.setId(null);
        String resourceJson = objectMapper.writeValueAsString(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resourceJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateResourceSuccessfullyReturnsHttpSavedResource() throws Exception {
        Resource resource =TestDataUtil.createTestResourceA();
        resource.setId(null);
        String resourceJson = objectMapper.writeValueAsString(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resourceJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(resource.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.onStock").value(resource.getOnStock())
        );
    }
}

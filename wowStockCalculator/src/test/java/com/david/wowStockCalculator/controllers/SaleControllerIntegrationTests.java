package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.dto.SaleDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class SaleControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public SaleControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatCreateSaleReturnsHttpStatus201Created() throws Exception {
        SaleDto saleDto = TestDataUtil.createTestSaleDtoA(null);
        ResourceDto resourceDto = TestDataUtil.createTestResourceDtoA();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceDto))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/sales/" + resourceDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saleDto))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateSaleReturnsCreatedSale() throws Exception {
        SaleDto saleDto = TestDataUtil.createTestSaleDtoA(null);
        ResourceDto resourceDto = TestDataUtil.createTestResourceDtoA();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceDto))
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/sales/" + resourceDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saleDto))
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(saleDto.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.amount").value(saleDto.getAmount())
        );
    }
}

package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.dto.SaleDto;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.services.ResourceService;
import com.david.wowStockCalculator.services.SaleService;
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
    private ResourceService resourceService;
    private SaleService saleService;

    @Autowired
    public SaleControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, ResourceService resourceService, SaleService saleService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.resourceService = resourceService;
        this.saleService = saleService;
    }

    @Test
    public void testThatCreateSaleReturnsHttpStatus201Created() throws Exception {
        SaleDto saleDto = TestDataUtil.createTestSaleDtoA(null);
        ResourceDto resourceDto = TestDataUtil.createTestResourceDtoA();

        mockMvc.perform(
                MockMvcRequestBuilders.put("/resources")
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
                MockMvcRequestBuilders.put("/resources")
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

    @Test
    public void testThatListSaleReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListSalePagedReturnsListOfSales() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        Sale sale = TestDataUtil.createTestSaleA(resource);
        saleService.createSale(resource.getId(), sale);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/salesPaged")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].date").value(sale.getDate())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.content[0].resource.id").isNumber()
        );
    }

    @Test
    public void testThatGetSaleByResourceIdReturnsCorrectResponseCodes() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        Sale sale = TestDataUtil.createTestSaleA(resource);
        saleService.createSale(resource.getId(), sale);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales/" + sale.getResource().getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetSalesByResourceIdReturnsSale() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        Sale sale = TestDataUtil.createTestSaleA(resource);
        saleService.createSale(resource.getId(), sale);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/sales/" + sale.getResource().getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].date").value(sale.getDate())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].resource.id").isNumber()
        );
    }

    @Test
    public void testThatDeleteSaleDeletesSaleCorrectly() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        Sale sale = TestDataUtil.createTestSaleA(resource);
        saleService.createSale(resource.getId(), sale);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/sales/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/sales/" + sale.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/sales/" + sale.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/resources/" + resource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
}

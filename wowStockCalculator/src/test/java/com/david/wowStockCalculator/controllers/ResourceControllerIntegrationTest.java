package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.services.ResourceService;
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
    private ResourceService resourceService;

    @Autowired
    public ResourceControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, ResourceService resourceService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.resourceService = resourceService;
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
        Resource resource = TestDataUtil.createTestResourceA();
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

    @Test
    public void testThatListResourceReturnsHttp200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListResourceReturnsListOfResources() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value(resource.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].onStock").value(resource.getOnStock())
        );
    }

    @Test
    public void testThatGetResourceReturnsCorrectResponseCodes() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/resources/" + resource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/resources/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetResourceReturnsResource() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/resources/" + resource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(resource.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.onStock").value(resource.getOnStock())
        );
    }

    @Test
    public void testThatPartialUpdateExistingResourceReturnsCorrectResource() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        resource.setName("Writhebark");
        String resourceJson = objectMapper.writeValueAsString(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/resources/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resourceJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/resources/" + resource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resourceJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(resource.getName())
        );
    }

    @Test
    public void testThatDeleteResourceDeletesResource() throws Exception {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceService.createResource(resource);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/resources/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/resources/" + resource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/resources/" + resource.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}

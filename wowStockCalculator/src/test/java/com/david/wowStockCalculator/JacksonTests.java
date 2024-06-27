package com.david.wowStockCalculator;

import com.david.wowStockCalculator.domain.entities.Sale;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Log
public class JacksonTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testThatObjectMapperCanCreateJsonFromObject() throws JsonProcessingException {
        Sale sale = TestDataUtil.createTestSaleA(TestDataUtil.createTestResourceA());

        String result = objectMapper.writeValueAsString(sale);

        log.info(result);
        Assertions.assertThat(result).isNotEqualTo("asd");
    }

    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
        String json = "{\"id\":1,\"resource\":{\"id\":1,\"name\":\"Dracothyst\",\"onStock\":1},\"date\":\"2024-06-27\",\"amount\":1,\"cost\":1}";

        Sale result = objectMapper.readValue(json, Sale.class);

        log.info(result.toString());
        Assertions.assertThat(result).isEqualTo(TestDataUtil.createTestSaleA(TestDataUtil.createTestResourceA()));
    }
}

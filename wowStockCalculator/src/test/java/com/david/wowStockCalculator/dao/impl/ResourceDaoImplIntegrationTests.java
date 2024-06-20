package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.Resource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ResourceDaoImplIntegrationTests {

    private ResourceDaoImpl underTest;

    @Autowired
    public ResourceDaoImplIntegrationTests(ResourceDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatResourceCanBeCreatedAndRecalled(){
        Resource resource = TestDataUtil.createTestResource();
        underTest.create(resource);
        Optional<Resource> result = underTest.findOne(resource.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(resource);
    }
}

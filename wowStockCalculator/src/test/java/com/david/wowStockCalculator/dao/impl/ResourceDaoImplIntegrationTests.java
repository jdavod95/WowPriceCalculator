package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.Resource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ResourceDaoImplIntegrationTests {

    private ResourceDaoImpl underTest;

    @Autowired
    public ResourceDaoImplIntegrationTests(ResourceDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatResourceCanBeCreatedAndRecalled(){
        Resource resource = TestDataUtil.createTestResourceA();
        underTest.create(resource);
        Optional<Resource> result = underTest.findOne(resource.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(resource);
    }

    @Test
    public void testThatMultipleResourceCanBeCreatedAndRecalled(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        Resource resourceB = TestDataUtil.createTestResourceB();

        underTest.create(resourceA);
        underTest.create(resourceB);

        List<Resource> result = underTest.find();

        Assertions.assertThat(result)
                .hasSize(2)
                .contains(resourceA)
                .contains(resourceB);
    }

    @Test
    public void testThatResourceCanBeUpdated(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        underTest.create(resourceA);

        resourceA.setOnStock(2);
        underTest.update(resourceA, 2);
        Optional<Resource> result = underTest.findOne(resourceA.getId());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(resourceA);
    }
}

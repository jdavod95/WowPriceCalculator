package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.entities.Resource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ResourceRepositoryIntegrationTests {

    private ResourceRepository underTest;

    @Autowired
    public ResourceRepositoryIntegrationTests(ResourceRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatResourceCanBeCreatedAndRecalled(){
        Resource resource = TestDataUtil.createTestResourceA();
        underTest.save(resource);
        Optional<Resource> result = underTest.findById(resource.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(resource);
    }

    @Test
    public void testThatMultipleResourceCanBeCreatedAndRecalled(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        Resource resourceB = TestDataUtil.createTestResourceB();

        underTest.save(resourceA);
        underTest.save(resourceB);

        Iterable<Resource> result = underTest.findAll();

        Assertions.assertThat(result)
                .hasSize(2)
                .contains(resourceA)
                .contains(resourceB);
    }

    @Test
    public void testThatResourceCanBeUpdated(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        underTest.save(resourceA);

        underTest.save(resourceA);
        Optional<Resource> result = underTest.findById(resourceA.getId());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(resourceA);
    }

    @Test
    public void testThatResourceCanBeDeleted(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        underTest.save(resourceA);

        underTest.delete(resourceA);

        Optional<Resource> result = underTest.findById(resourceA.getId());
        Assertions.assertThat(result).isEmpty();
    }

}

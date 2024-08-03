package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
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
public class SaleRepositoryIntegrationTest {

    private final ResourceRepository resources;

    private final SaleRepository underTest;

    @Autowired
    public SaleRepositoryIntegrationTest(SaleRepository underTest, ResourceRepository resources) {
        this.resources = resources;
        this.underTest = underTest;
    }

    @Test
    public void testThatSaleCanBeCreatedAndRecalled() {
        Resource resourceA = TestDataUtil.createTestResourceA();

        Sale sale = TestDataUtil.createTestSaleA(resourceA);

        underTest.save(sale);
        Optional<Sale> result = underTest.findById(sale.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(sale);
    }

    @Test
    public void testThatMultipleSaleCanBeCreatedAndRecalled(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        Resource resourceB = TestDataUtil.createTestResourceB();

        Sale saleA = TestDataUtil.createTestSaleA(resourceA);
        Sale saleB = TestDataUtil.createTestSaleB(resourceB);

        underTest.save(saleA);
        underTest.save(saleB);

        Iterable<Sale> result = underTest.findAll();

        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(saleA, saleB);
    }

    @Test
    public void testThatSaleCanBeUpdated(){
        Resource resourceA = TestDataUtil.createTestResourceA();

        Sale sale = TestDataUtil.createTestSaleA(resourceA);
        underTest.save(sale);

        sale.setCost(10L);
        underTest.save(sale);
        Optional<Sale> result = underTest.findById(sale.getId());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(sale);
    }

    @Test
    public void testThatSaleCanBeDeleted(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        Sale sale = TestDataUtil.createTestSaleA(resourceA);

        underTest.save(sale);
        underTest.delete(sale);

        Assertions.assertThat(underTest.findById(sale.getId())).isEmpty();
        Assertions.assertThat(resources.findById(resourceA.getId())).isPresent();
    }

    @Test
    public void testThatSaleDateAfter(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        Sale saleA = TestDataUtil.createTestSaleA(resourceA);
        Sale saleB = TestDataUtil.createTestSaleB(resourceA);

        underTest.save(saleA);
        underTest.save(saleB);

        Iterable<Sale> result = underTest.findSalesAfterYesterday(TestDataUtil.getYesterday());
        Assertions.assertThat(result).containsExactly(saleA, saleB);
    }
}

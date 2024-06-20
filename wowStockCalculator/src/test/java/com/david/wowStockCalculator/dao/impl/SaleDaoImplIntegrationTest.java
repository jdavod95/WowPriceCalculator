package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.dao.ResourceDao;
import com.david.wowStockCalculator.domain.Resource;
import com.david.wowStockCalculator.domain.Sale;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SaleDaoImplIntegrationTest {

    private ResourceDao resourceDao;

    private final SaleDaoImpl underTest;

    @Autowired
    public SaleDaoImplIntegrationTest(SaleDaoImpl underTest, ResourceDao resourceDao) {
        this.underTest = underTest;
        this.resourceDao = resourceDao;
    }

    @Test
    public void testThatSaleCanBeCratedAndRecalled() {
        Resource resource = TestDataUtil.createTestResourceA();
        resourceDao.create(resource);

        Sale sale = TestDataUtil.createTestSaleA();
        sale.setResourceId(resource.getId());

        underTest.create(sale);
        Optional<Sale> result = underTest.findOne(sale.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(sale);
    }

    @Test
    public void testThatMultipleSaleCanBeCreatedAndRecalled(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        Resource resourceB = TestDataUtil.createTestResourceB();

        resourceDao.create(resourceA);
        resourceDao.create(resourceB);

        Sale saleA = TestDataUtil.createTestSaleA();
        Sale saleB = TestDataUtil.createTestSaleB();
        saleA.setResourceId(resourceA.getId());
        saleB.setResourceId(resourceB.getId());

        underTest.create(saleA);
        underTest.create(saleB);

        List<Sale> result = underTest.find();

        Assertions.assertThat(result)
                .hasSize(2)
                .containsExactly(saleA, saleB);
    }

    @Test
    public void testThatSaleCanBeUpdated(){
        Resource resourceA = TestDataUtil.createTestResourceA();
        resourceDao.create(resourceA);

        Sale sale = TestDataUtil.createTestSaleA();
        sale.setResourceId(resourceA.getId());
        underTest.create(sale);

        sale.setCost(10);
        underTest.update(sale, 10);
        Optional<Sale> result = underTest.findOne(sale.getId());

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(sale);
    }
}

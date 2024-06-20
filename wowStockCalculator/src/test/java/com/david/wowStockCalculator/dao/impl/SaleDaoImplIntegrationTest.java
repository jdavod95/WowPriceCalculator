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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
        Resource resource = TestDataUtil.createTestResource();
        resourceDao.create(resource);

        Date now = DateUtil.truncateTime(DateUtil.now());
        Sale sale = TestDataUtil.createTestSale(now);
        sale.setResourceId(resource.getId());

        underTest.create(sale);
        Optional<Sale> result = underTest.findOne(sale.getId());
        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(sale);
    }
}

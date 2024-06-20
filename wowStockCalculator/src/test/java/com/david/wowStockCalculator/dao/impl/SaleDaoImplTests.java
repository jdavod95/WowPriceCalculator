package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.dao.ResourceDao;
import com.david.wowStockCalculator.domain.Resource;
import com.david.wowStockCalculator.domain.Sale;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SaleDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SaleDaoImpl underTest;

    @Test
    public void testThatCreateSaleGeneratesCorrectSQL(){
        Date now = DateUtil.truncateTime(DateUtil.now());
        Sale sale = Sale.builder()
                .id(1L)
                .date(now)
                .resourceId(1L)
                .amount(1)
                .cost(1)
                .build();
        //TestDataUtil.createTestSaleA(now);

        underTest.create(sale);

        verify(jdbcTemplate)
                .update(
                        eq("INSERT INTO sale (id, date, resource_id, amount, cost) VALUES (?, ?, ?, ?, ?)"),
                        eq(1L),
                        eq(now),
                        eq(1L),
                        eq(1),
                        eq(1)
                );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSQL() {
        underTest.findOne(1L);

        verify(jdbcTemplate)
                .query(
                        eq("SELECT id, date, resource_id, amount, cost FROM sale WHERE id = ? LIMIT 1"),
                        ArgumentMatchers.<SaleDaoImpl.SaleRowMapper>any(),
                        eq(1L)
                );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSQL(){
        Sale sale = TestDataUtil.createTestSaleA();
        underTest.update(sale, 10);

        verify(jdbcTemplate).update(
                "UPDATE sale SET id = ?, date = ?, resource_id = ?, amount = ?, cost = ? WHERE id = ?",
                sale.getId(),
                sale.getDate(),
                sale.getResourceId(),
                sale.getAmount(),
                10,
                sale.getId()
        );
    }
}

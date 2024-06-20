package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.Sale;
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
    public void testThatCreateAuthorGeneratesCorrectSQL(){
        Date now = new Date();
        Sale sale = TestDataUtil.createTestSale(now);

        underTest.create(sale);

        verify(jdbcTemplate)
                .update(
                        eq("INSERT INTO sale (id, date, resource_id, amount, cost) VALUES(?, ?, ?, ?, ?)"),
                        eq(1L), eq(now), eq(1L), eq(1), eq(1)
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
}

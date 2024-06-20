package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.TestDataUtil;
import com.david.wowStockCalculator.domain.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ResourceDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ResourceDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSQL() {
        Resource resource = TestDataUtil.createTestResource();

        underTest.create(resource);

        verify(jdbcTemplate)
                .update(
                        eq("INSERT INTO resource (id, name, on_stock) VALUES (?, ?, ?)"),
                        eq(1L), eq("Dracothyst"), eq(0)
                );
    }

    @Test
    public void testThatFindOneGeneratesCorrectSQL() {
        underTest.findOne(1L);

        verify(jdbcTemplate)
                .query(
                        eq("SELECT id, name, on_stock FROM resource WHERE id = ? LIMIT 1"),
                        ArgumentMatchers.<ResourceDaoImpl.ResourceRowMapper>any(),
                        eq(1L)
                );
    }

}

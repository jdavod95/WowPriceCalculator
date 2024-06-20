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
    public void testThatCreateResourceGeneratesCorrectSQL() {
        Resource resource = TestDataUtil.createTestResourceA();

        underTest.create(resource);

        verify(jdbcTemplate).update(eq("INSERT INTO resource (id, name, on_stock) VALUES (?, ?, ?)"), eq(1L), eq("Dracothyst"), eq(0));
    }

    @Test
    public void testThatFindOneGeneratesCorrectSQL() {
        underTest.findOne(1L);

        verify(jdbcTemplate).query(eq("SELECT id, name, on_stock FROM resource WHERE id = ? LIMIT 1"), ArgumentMatchers.<ResourceDaoImpl.ResourceRowMapper>any(), eq(1L));
    }

    @Test
    public void testThatFindManyGeneratesCorrectSQL() {
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, on_stock FROM resource"),
                ArgumentMatchers.<ResourceDaoImpl.ResourceRowMapper>any());
    }

    @Test
    public void testThatUpdateGeneratesCorrectSQL(){
        Resource resource = TestDataUtil.createTestResourceA();
        underTest.update(resource, 2);

        verify(jdbcTemplate).update(
                "UPDATE resource SET id = ?, name = ?, on_stock = ? WHERE id = ?",
                1L,
                "Dracothyst",
                2,
                1L
        );
    }

}

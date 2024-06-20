package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.dao.SaleDao;
import com.david.wowStockCalculator.domain.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class SaleDaoImpl implements SaleDao {

    private final JdbcTemplate jdbcTemplate;

    public SaleDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Sale sale) {
        jdbcTemplate.update(
            "INSERT INTO sale (id, date, resource_id, amount, cost) VALUES (?, ?, ?, ?, ?)",
                    sale.getId(),
                    sale.getDate(),
                    sale.getResourceId(),
                    sale.getAmount(),
                    sale.getCost()
                );
    }

    @Override
    public Optional<Sale> findOne(long saleId) {
        List<Sale> results = jdbcTemplate.query(
                "SELECT id, date, resource_id, amount, cost FROM sale WHERE id = ? LIMIT 1",
                new SaleDaoImpl.SaleRowMapper(), saleId);

        return results.stream().findFirst();
    }

    @Override
    public List<Sale> find() {
        return jdbcTemplate.query("SELECT id, date, resource_id, amount, cost FROM sale",
                new SaleDaoImpl.SaleRowMapper());
    }

    @Override
    public void update(Sale sale, int cost) {
        jdbcTemplate.update("UPDATE sale SET id = ?, date = ?, resource_id = ?, amount = ?, cost = ? WHERE id = ?",
                sale.getId(),
                sale.getDate(),
                sale.getResourceId(),
                sale.getAmount(),
                cost,
                sale.getId()
            );
    }

    public static class SaleRowMapper implements RowMapper<Sale> {

        @Override
        public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Sale.builder()
                    .id(rs.getLong("id"))
                    .date(rs.getDate("date"))
                    .resourceId(rs.getLong("resource_id"))
                    .amount(rs.getInt("amount"))
                    .cost(rs.getInt("cost"))
                    .build();
        }
    }
}

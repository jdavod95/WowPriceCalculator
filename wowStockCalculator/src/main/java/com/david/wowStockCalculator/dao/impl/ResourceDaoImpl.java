package com.david.wowStockCalculator.dao.impl;

import com.david.wowStockCalculator.dao.ResourceDao;
import com.david.wowStockCalculator.domain.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class ResourceDaoImpl implements ResourceDao {

    private final JdbcTemplate jdbcTemplate;

    public ResourceDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Resource resource) {
        jdbcTemplate.update(
                "INSERT INTO resource (id, name, on_stock) VALUES (?, ?, ?)",
                    resource.getId(),
                    resource.getName(),
                    resource.getOnStock()
        );
    }

    @Override
    public Optional<Resource> findOne(long resourceId) {
        List<Resource> results = jdbcTemplate.query(
                "SELECT id, name, on_stock FROM resource WHERE id = ? LIMIT 1",
                    new ResourceRowMapper(), resourceId);

        return results.stream().findFirst();
    }

    public static class ResourceRowMapper implements RowMapper<Resource> {

        @Override
        public Resource mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Resource.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .onStock(rs.getInt("on_stock"))
                    .build();
        }
    }
}

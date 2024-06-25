package com.david.wowStockCalculator.dao;

import com.david.wowStockCalculator.domain.Resource;
import com.david.wowStockCalculator.domain.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleDao {

    void create(Sale sale);

    Optional<Sale> findOne(long l);

    List<Sale> find();

    void update(Sale sale, int cost);

    void delete(Long id);
}

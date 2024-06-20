package com.david.wowStockCalculator.dao;

import com.david.wowStockCalculator.domain.Sale;

import java.util.Optional;

public interface SaleDao {

    void create(Sale sale);

    Optional<Sale> findOne(long l);
}

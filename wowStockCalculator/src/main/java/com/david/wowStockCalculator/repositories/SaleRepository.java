package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {
}

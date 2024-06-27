package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

    @Query("SELECT a FROM Sale a WHERE a.date > ?1")
    Iterable<Sale> findSalesAfterYesterday(String yesterday);
}

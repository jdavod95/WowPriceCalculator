package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long>,
        PagingAndSortingRepository<Sale, Long> {

    @Query("SELECT a FROM Sale a WHERE a.date > ?1")
    Iterable<Sale> findSalesAfterYesterday(String yesterday);

    @Query("SELECT a FROM Sale a WHERE a.resource.id = ?1")
    Iterable<Sale> findAllByResourceId(Long resourceId);
}

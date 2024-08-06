package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long>,
        PagingAndSortingRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.date > ?1")
    Iterable<Sale> findSalesAfterYesterday(String yesterday);

    Iterable<Sale> findAllByResourceId(Long resourceId);

    Page<Sale> findAllByResourceId(Long resourceId, Pageable pageable);
}

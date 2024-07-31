package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.entities.StockMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockMappingRepository extends CrudRepository<StockMapping, Long>,
        PagingAndSortingRepository<StockMapping, Long> {

    Iterable<StockMapping> findAllByResourceId(Long resourceId);

    Optional<StockMapping> findByResourceIdAndValue(Long resourceId, Long value);

    List<StockMapping> findByIdIn(List<Long> stockMappingIds);
}

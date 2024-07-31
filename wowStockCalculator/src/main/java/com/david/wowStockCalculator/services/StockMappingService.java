package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.services.impl.StockMappingServiceImpl;

import java.util.List;
import java.util.Optional;

public interface StockMappingService {

    StockMapping save(StockMapping stockMapping) throws StockMappingServiceImpl.ZeroAmountException;

    StockMapping save(Long resourceId, Long amount, Long value, Boolean isSold) throws StockMappingServiceImpl.ZeroAmountException;

    StockMapping getStockMapping(Long resourceId, Long amount, Long value);

    void delete(Long id);

    Iterable<StockMapping> findAllByResourceId(Long resourceId);

    Optional<StockMapping> findByResourceIdAndValue(Long resourceId, Long price);

    List<StockMapping> findByIdIn(List<Long> stockMappingIds);

    StockMapping merge(StockMapping target, List<StockMapping> source) throws StockMappingServiceImpl.ZeroAmountException;
}

package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.services.impl.StockMappingServiceImpl;

import java.util.Optional;

public interface StockMappingService {

    StockMapping save(StockMapping stockMapping);

    StockMapping save(Long resourceId, Integer amount, Long value, Boolean isSold) throws StockMappingServiceImpl.ZeroAmountException;

    void delete(Long id);

    Iterable<StockMapping> findAllByResourceId(Long resourceId);

    Optional<StockMapping> findByResourceIdAndValue(Long resourceId, Long price);
}

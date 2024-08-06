package com.david.wowStockCalculator.mappers;

import com.david.wowStockCalculator.domain.dto.ResourceStockDto;
import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.services.StockMappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.StreamSupport;

public abstract class ResourceStockMapper {

    @Autowired
    protected StockMappingService stockMappingService;

    protected ResourceStockDto mapStock(ResourceStockDto dto){
        Iterable<StockMapping> stocks = stockMappingService.findAllByResourceId(dto.getId());

        dto.setAmount(
                StreamSupport.stream(stocks.spliterator(), false)
                        .mapToLong(StockMapping::getAmount)
                        .sum()
        );

        long top = 0;
        long bottom = 0;

        for (StockMapping stockMapping : stocks) {
            top += (stockMapping.getValue() * stockMapping.getAmount());
            bottom += stockMapping.getAmount();
        }
        if(top != 0 && bottom != 0) {
            dto.setValue(top / bottom);
        } else {
            dto.setValue(0L);
        }
        return dto;
    }
}

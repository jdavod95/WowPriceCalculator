package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.ReagentDto;
import com.david.wowStockCalculator.domain.dto.StockMappingDto;
import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StockMappingMapperImpl implements Mapper<StockMapping, StockMappingDto> {

    private ModelMapper modelMapper;

    public StockMappingMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public StockMappingDto mapTo(StockMapping stockMapping) {
        return modelMapper.map(stockMapping, StockMappingDto.class);
    }

    @Override
    public StockMapping mapFrom(StockMappingDto stockMappingDto) {
        return modelMapper.map(stockMappingDto, StockMapping.class);
    }

}

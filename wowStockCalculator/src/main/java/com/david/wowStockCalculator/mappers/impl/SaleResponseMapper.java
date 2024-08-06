package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.SaleResponseDto;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SaleResponseMapper implements Mapper<Sale, SaleResponseDto> {

    private ModelMapper modelMapper;

    public SaleResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SaleResponseDto mapTo(Sale sale) {
        return modelMapper.map(sale, SaleResponseDto.class);
    }

    @Override
    public Sale mapFrom(SaleResponseDto saleResponseDto) {
        return modelMapper.map(saleResponseDto, Sale.class);
    }
}

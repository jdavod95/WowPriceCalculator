package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.dto.ResourceStockDto;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.mappers.ResourceStockMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceStockDtoMapperImpl extends ResourceStockMapper implements Mapper<ResourceDto, ResourceStockDto> {

    private ModelMapper modelMapper;

    public ResourceStockDtoMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResourceStockDto mapTo(ResourceDto resourceDto) {
        ResourceStockDto dto = modelMapper.map(resourceDto, ResourceStockDto.class);
        return mapStock(dto);
    }

    @Override
    public ResourceDto mapFrom(ResourceStockDto resourceStockDto) {
        return modelMapper.map(resourceStockDto, ResourceDto.class);
    }

}

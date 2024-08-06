package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.ResourceStockDto;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.mappers.ResourceStockMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceStockMapperImpl extends ResourceStockMapper implements Mapper<Resource, ResourceStockDto> {

    private ModelMapper modelMapper;

    public ResourceStockMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResourceStockDto mapTo(Resource resource) {
        ResourceStockDto dto = modelMapper.map(resource, ResourceStockDto.class);
        return mapStock(dto);
    }

    @Override
    public Resource mapFrom(ResourceStockDto resourceStockDto) {
        return modelMapper.map(resourceStockDto, Resource.class);
    }
}

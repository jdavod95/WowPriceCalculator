package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapperImpl implements Mapper<Resource, ResourceDto> {

    private ModelMapper modelMapper;

    public ResourceMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResourceDto mapTo(Resource resource) {
        return modelMapper.map(resource, ResourceDto.class);
    }

    @Override
    public Resource mapFrom(ResourceDto resourceDto) {
        return modelMapper.map(resourceDto, Resource.class);
    }
}

package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.ReagentDto;
import com.david.wowStockCalculator.domain.dto.ReagentResponseDto;
import com.david.wowStockCalculator.domain.dto.ResourceStockDto;
import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ReagentStockMapperImpl implements Mapper<Reagent, ReagentResponseDto> {

    private ModelMapper modelMapper;
    private Mapper<Resource, ResourceStockDto> resourceStockMapper;

    public ReagentStockMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReagentResponseDto mapTo(Reagent reagent) {
        ReagentResponseDto dto = modelMapper.map(reagent, ReagentResponseDto.class);
        dto.setResource(resourceStockMapper.mapTo(reagent.getResource()));
        return dto;
    }

    @Override
    public Reagent mapFrom(ReagentResponseDto recipeReagentResponseDto) {
        return modelMapper.map(recipeReagentResponseDto, Reagent.class);
    }

}

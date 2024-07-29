package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.ReagentDto;
import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReagentMapperImpl implements Mapper<Reagent, ReagentDto> {

    private ModelMapper modelMapper;

    public ReagentMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReagentDto mapTo(Reagent reagent) {
        return modelMapper.map(reagent, ReagentDto.class);
    }

    @Override
    public Reagent mapFrom(ReagentDto recipeReagentDto) {
        return modelMapper.map(recipeReagentDto, Reagent.class);
    }

}

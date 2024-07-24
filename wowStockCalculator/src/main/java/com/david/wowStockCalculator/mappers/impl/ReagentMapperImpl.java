package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.RecipeReagentDto;
import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecipeReagentMapperImpl implements Mapper<Reagent, RecipeReagentDto> {

    private ModelMapper modelMapper;

    public RecipeReagentMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeReagentDto mapTo(Reagent reagent) {
        return modelMapper.map(reagent, RecipeReagentDto.class);
    }

    @Override
    public Reagent mapFrom(RecipeReagentDto recipeReagentDto) {
        return modelMapper.map(recipeReagentDto, Reagent.class);
    }
}

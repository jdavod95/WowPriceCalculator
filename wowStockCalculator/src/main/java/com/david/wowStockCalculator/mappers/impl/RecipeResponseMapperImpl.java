package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.RecipeDto;
import com.david.wowStockCalculator.domain.dto.RecipeResponseDto;
import com.david.wowStockCalculator.domain.entities.Recipe;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class RecipeResponseMapperImpl implements Mapper<Recipe, RecipeResponseDto> {

    private ModelMapper modelMapper;

    public RecipeResponseMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeResponseDto mapTo(Recipe recipe) {
        return modelMapper.map(recipe, RecipeResponseDto.class);
    }

    @Override
    public Recipe mapFrom(RecipeResponseDto recipeResponseDto) {
        return modelMapper.map(recipeResponseDto, Recipe.class);
    }
}

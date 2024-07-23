package com.david.wowStockCalculator.mappers.impl;

import com.david.wowStockCalculator.domain.dto.RecipeDto;
import com.david.wowStockCalculator.domain.entities.Recipe;
import com.david.wowStockCalculator.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class RecipeMapperImpl implements Mapper<Recipe, RecipeDto> {

    private ModelMapper modelMapper;

    public RecipeMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecipeDto mapTo(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDto.class);
    }

    @Override
    public Recipe mapFrom(RecipeDto recipeDto) {
        return modelMapper.map(recipeDto, Recipe.class);
    }
}

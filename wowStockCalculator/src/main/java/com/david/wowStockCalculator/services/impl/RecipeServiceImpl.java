package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.domain.entities.Recipe;
import com.david.wowStockCalculator.repositories.ReagentRepository;
import com.david.wowStockCalculator.repositories.RecipeRepository;
import com.david.wowStockCalculator.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private ReagentRepository reagentRepository;

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Recipe createRecipe(Recipe recipe) {
        recipe.getRequiredReagents().forEach(reagent -> reagentRepository.save(reagent));
        recipe.getResultingReagents().forEach(reagent -> reagentRepository.save(reagent));
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findAll() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        recipeRepository.delete(recipe);
    }


    @Override
    public boolean isExists(Long id) {
        return recipeRepository.existsById(id);
    }
}

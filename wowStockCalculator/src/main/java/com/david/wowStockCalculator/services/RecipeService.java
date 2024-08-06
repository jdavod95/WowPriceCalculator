package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    Recipe createRecipe(Recipe recipe);

    List<Recipe> findAll();

    Optional<Recipe> findById(Long id);

    void delete(Long id);

    boolean isExists(Long id);
}

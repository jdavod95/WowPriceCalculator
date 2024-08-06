package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.entities.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>,
        PagingAndSortingRepository<Recipe, Long> {

}

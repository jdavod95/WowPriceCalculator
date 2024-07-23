package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.ReagentDto;
import com.david.wowStockCalculator.domain.dto.RecipeDto;
import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.domain.entities.Recipe;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.RecipeService;
import com.david.wowStockCalculator.services.ResourceService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@Log
@AllArgsConstructor
public class RecipeController {

    private RecipeService recipeService;
    private ResourceService resourceService;
    private Mapper<Recipe, RecipeDto> recipeMapper;
    private Mapper<Reagent, ReagentDto> reagentMapper;

    @GetMapping(path = "/recipes")
    public List<RecipeDto> listRecipe(){
        List<Recipe> recipes = recipeService.findAll();
        return recipes.stream()
                .map(recipeMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/recipes/{recipe_id}")
    public ResponseEntity<RecipeDto> getRecipe(
            @PathVariable("recipe_id") Long recipeId
    ){
        Optional<Recipe> foundRecipe = recipeService.findById(recipeId);
        return foundRecipe.map(
                recipe -> new ResponseEntity<>(recipeMapper.mapTo(recipe), HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping(path = "/recipes")
    public ResponseEntity<RecipeDto> createRecipe(
            @RequestBody final RecipeDto recipe){
        List<Reagent> reagents = recipe.getReagents().stream()
                .map(reagentMapper::mapFrom)
                .collect(Collectors.toList());

        reagents.forEach(reagent -> {
            reagent.setResource(resourceService.findById(reagent.getResource().getId()).get());
        });

        Recipe recipeEntity = recipeMapper.mapFrom(recipe);
        recipeEntity.setReagents(reagents);

        Recipe savedRecipe = recipeService.createRecipe(recipeEntity);
        return new ResponseEntity<>(recipeMapper.mapTo(savedRecipe), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/recipes/{recipe_id}")
    public ResponseEntity delete(
            @PathVariable("recipe_id") Long recipeId
    ) {
        if(!recipeService.isExists(recipeId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.delete(recipeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.backend.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.backend.recipe.RecipeApplication.jsonData;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @GetMapping()
    public ResponseEntity<Map<String, List<String>>> getRecipeNames() {
        List<String> names = new ArrayList<>();
        jsonData.getRecipes().forEach(r -> names.add(r.getName()));

        Map<String, List<String>> map = new HashMap<>();
        map.put("recipeNames", names);

        return ResponseEntity.ok(map);
    }

    @GetMapping("/details/{recipeName}")
    public ResponseEntity getRecipeDetails(@PathVariable String recipeName) {
        Optional<Recipe> recipe = jsonData.getRecipes().stream()
                                                        .filter(r -> r.getName().equals(recipeName))
                                                        .findFirst();

        Map<String, Details> map = new HashMap<>();

        if(recipe.isPresent()) {
            Details details = new Details(recipe.get().getIngredients(), recipe.get().getIngredients().size());
            map.put("details", details);
        }

        return ResponseEntity.ok(map);
    }

    @PostMapping()
    public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
        Optional<Recipe> recipeInList = jsonData.getRecipes().stream()
                                                                .filter(r -> r.getName().equals(recipe.getName()))
                                                                .findFirst();

        if(recipeInList.isPresent()) {
            Map<String, String> map = new HashMap<>();
            map.put("error", "Recipe already exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        } else {
            jsonData.getRecipes().add(recipe);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }

    @PutMapping()
    public ResponseEntity updateRecipe(@RequestBody Recipe recipe) {
        Optional<Recipe> recipeInList = jsonData.getRecipes().stream()
                                                                .filter(r -> r.getName().equals(recipe.getName()))
                                                                .findFirst();

        if(recipeInList.isPresent()) {
            jsonData.getRecipes().remove(recipeInList.get());
            jsonData.getRecipes().add(recipe);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("error", "Recipe does not exist");
            return new ResponseEntity(map, HttpStatus.NOT_FOUND);
        }
    }
}

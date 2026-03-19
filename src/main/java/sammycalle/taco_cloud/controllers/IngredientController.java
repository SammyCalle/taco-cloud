/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sammycalle.taco_cloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sammycalle.taco_cloud.domain.model.Ingredient;

/**
 *
 * @author sammy
 */

@Controller
public class IngredientController {

    private final RestTemplate restTemplate;

    public IngredientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    } 

    public Ingredient getIngredientById(String ingredientId){
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}",
         Ingredient.class, ingredientId);
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}",
        ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}",
            ingredient.getId());    
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject("http://localhost:8080/ingredients",
            ingredient, Ingredient.class);
    }
}

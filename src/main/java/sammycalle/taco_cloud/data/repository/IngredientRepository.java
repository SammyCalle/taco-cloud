package sammycalle.taco_cloud.data.repository;

import java.util.Optional;

import sammycalle.taco_cloud.domain.Ingredient;

public interface IngredientRepository {
    
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);

}

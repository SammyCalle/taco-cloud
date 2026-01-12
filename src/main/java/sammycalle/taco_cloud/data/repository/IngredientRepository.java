package sammycalle.taco_cloud.data.repository;

import org.springframework.data.repository.CrudRepository;

import sammycalle.taco_cloud.domain.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

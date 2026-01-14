package sammycalle.taco_cloud.domain.converters;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sammycalle.taco_cloud.data.repository.IngredientRepository;
import sammycalle.taco_cloud.domain.model.Ingredient;
import sammycalle.taco_cloud.domain.model.IngredientUDT;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

  private final IngredientRepository ingredientRepository;

  public StringToIngredientConverter(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }
  
  @Override
  public IngredientUDT convert(String id) {
    Optional<Ingredient> ingredient = ingredientRepository.findById(id);
    if (ingredient.isEmpty()) {
      return null;
    }
    
    return ingredient.map(i -> {
      return new IngredientUDT(i.getName(), i.getType());
    }).get();
  }
  
}

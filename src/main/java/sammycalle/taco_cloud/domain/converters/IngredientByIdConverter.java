package sammycalle.taco_cloud.domain.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sammycalle.taco_cloud.data.repository.IngredientRepository;
import sammycalle.taco_cloud.domain.model.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String,  Ingredient> {
    
    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
    
}

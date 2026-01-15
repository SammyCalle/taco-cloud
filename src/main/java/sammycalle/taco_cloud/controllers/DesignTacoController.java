package sammycalle.taco_cloud.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import sammycalle.taco_cloud.data.repository.IngredientRepository;
import sammycalle.taco_cloud.data.repository.TacoRepository;
import sammycalle.taco_cloud.domain.model.Ingredient;
import sammycalle.taco_cloud.domain.model.Ingredient.Type;
import sammycalle.taco_cloud.domain.model.Taco;
import sammycalle.taco_cloud.domain.model.TacoOrder;
import sammycalle.taco_cloud.security.model.User;
import sammycalle.taco_cloud.security.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    private final UserRepository userRepository;

    public DesignTacoController(
        IngredientRepository ingredientRepository,
        TacoRepository tacoRepository,
        UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
  }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));
        
        Type[] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
             filterByType(ingredients,type));
        }
    }

    @ModelAttribute(name="order")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
            String username = principal.getName();
            User user = userRepository.findByUsername(username);
            return user;
    }

    @GetMapping
    public String showDesignFrom() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
         @ModelAttribute TacoOrder tacoOrder) {

        if(errors.hasErrors()){
            return "design";
        }
        
        Taco saved = tacoRepository.save(taco);
        tacoOrder.addTaco(saved);

        return "redirect:/orders/current";
    }
    

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
    }
}

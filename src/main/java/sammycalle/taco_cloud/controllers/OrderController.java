package sammycalle.taco_cloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import sammycalle.taco_cloud.data.repository.OrderRepository;
import sammycalle.taco_cloud.domain.model.TacoOrder;



@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping()
    public String processOrder(@Valid TacoOrder order, Errors errors,
        SessionStatus sessionStatus) {

        if(errors.hasErrors()){
            return "orderForm";
        }

        orderRepository.save(order);

        
        log.info("Order submitted: {}",order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
    
    
}

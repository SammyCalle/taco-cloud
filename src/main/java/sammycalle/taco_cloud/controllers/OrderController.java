package sammycalle.taco_cloud.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import sammycalle.taco_cloud.data.repository.OrderRepository;
import sammycalle.taco_cloud.domain.model.TacoOrder;
import sammycalle.taco_cloud.security.model.User;



@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
      @ModelAttribute TacoOrder order) {
        if (order.getDeliveryName() == null) {
        order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
        order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
        order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
        order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
        order.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping()
    public String processOrder(@Valid TacoOrder order, Errors errors,
        SessionStatus sessionStatus,@AuthenticationPrincipal User user) {

        if(errors.hasErrors()){
            return "orderForm";
        }

        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
    
    
}

package sammycalle.taco_cloud.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PutMapping(path="/{orderId}", consumes="application/json")
     public TacoOrder putTaco(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order) {
         order.setId(orderId);
         return orderRepository.save(order);
     }

     @PatchMapping(path="/{orderId}", consumes="application/json")
     public TacoOrder patchOrcer(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder orderPatch ){
        TacoOrder order = orderRepository.findById(orderId).get();
        if (orderPatch.getDeliveryName() != null) {
            order.setDeliveryName(orderPatch.getDeliveryName());
        }
        if (orderPatch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(orderPatch.getDeliveryStreet());
        }
        if (orderPatch.getDeliveryCity() != null) {
            order.setDeliveryCity(orderPatch.getDeliveryCity());
        }
        if (orderPatch.getDeliveryState() != null) {
            order.setDeliveryState(orderPatch.getDeliveryState());
        }
        if (orderPatch.getDeliveryZip() != null) {
            order.setDeliveryZip(orderPatch.getDeliveryZip());
        }
        if (orderPatch.getCcNumber() != null) {
            order.setCcNumber(orderPatch.getCcNumber());
        }
        if (orderPatch.getCcExpiration() != null) {
            order.setCcExpiration(orderPatch.getCcExpiration());
        }
        if (orderPatch.getCcCVV() != null) {
            order.setCcCVV(orderPatch.getCcCVV());
        }
        return orderRepository.save(order);
     }

     @DeleteMapping("/{orderId}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deleteOrder (@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
     }
    
    
}

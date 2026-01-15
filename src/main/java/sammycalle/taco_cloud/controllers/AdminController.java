package sammycalle.taco_cloud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sammycalle.taco_cloud.data.repository.service.OrderAdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderAdminService orderAdminService;

    public AdminController(OrderAdminService orderAdminService) {
        this.orderAdminService = orderAdminService;
    }

    
    @PostMapping("/deleteOrders")
    public String deleteAllOrdersRedirect() {
        deleteAllOrders();
        return "redirect:/admin";
    }
    
    private void deleteAllOrders(){
        orderAdminService.deleteAllOrders();
    }

    @GetMapping
    public String showAdminPage() {
        return "admin";
    }
    
}

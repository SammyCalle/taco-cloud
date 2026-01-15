package sammycalle.taco_cloud.data.repository.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import sammycalle.taco_cloud.data.repository.OrderRepository;

@Service
public class OrderAdminService {
    private final OrderRepository orderRepository;

  public OrderAdminService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @PreAuthorize("hasRole('ADMIN')")
  public void deleteAllOrders() {
    orderRepository.deleteAll();
  }
}

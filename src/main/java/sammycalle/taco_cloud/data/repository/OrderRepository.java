package sammycalle.taco_cloud.data.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import sammycalle.taco_cloud.domain.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID>{

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    
}

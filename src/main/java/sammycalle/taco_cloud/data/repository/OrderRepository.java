package sammycalle.taco_cloud.data.repository;

import sammycalle.taco_cloud.domain.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder tacoOrder);
}

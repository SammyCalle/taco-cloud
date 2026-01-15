package sammycalle.taco_cloud.data.repository;

import org.springframework.data.repository.CrudRepository;

import sammycalle.taco_cloud.domain.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}

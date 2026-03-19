package sammycalle.taco_cloud.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sammycalle.taco_cloud.domain.model.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}

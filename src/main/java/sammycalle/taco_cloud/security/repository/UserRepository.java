package sammycalle.taco_cloud.security.repository;

import org.springframework.data.repository.CrudRepository;

import sammycalle.taco_cloud.security.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}

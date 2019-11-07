package pl.angler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    boolean existsByEmail(String email);
}

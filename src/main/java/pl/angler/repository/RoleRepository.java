package pl.angler.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}

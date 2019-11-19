package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

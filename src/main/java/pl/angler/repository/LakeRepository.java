package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Lake;

import java.util.Optional;

@Repository
public interface LakeRepository extends JpaRepository<Lake, Long> {

    Optional<Lake> findByName(String name);
}

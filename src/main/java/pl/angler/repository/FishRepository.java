package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
}

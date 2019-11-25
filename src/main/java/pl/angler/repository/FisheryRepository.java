package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Fishery;

@Repository
public interface FisheryRepository extends JpaRepository<Fishery, Long> {
}

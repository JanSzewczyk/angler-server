package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.FishingTrip;

import java.util.List;

@Repository
public interface FishingTripRepository extends JpaRepository<FishingTrip, Long> {

    List<FishingTrip> findByUser_emailOrderByTripDateDesc(String email);
}

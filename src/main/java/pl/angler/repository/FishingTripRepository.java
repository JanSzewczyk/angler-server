package pl.angler.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.FishingTrip;

import java.util.List;
import java.util.Optional;

@Repository
public interface FishingTripRepository extends JpaRepository<FishingTrip, Long> {

    List<FishingTrip> findByUser_emailOrderByTripDateDesc(String email);
    Optional<FishingTrip> findByUser_emailAndId(String email, Long id);
    Boolean existsByUser_emailAndId(String email, Long Id);
}

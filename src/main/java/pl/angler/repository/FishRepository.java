package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Fish;

import java.util.List;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long> {
    List<Fish> findDistinctByTrophies_FishingTrip_User_Email(String email);
}

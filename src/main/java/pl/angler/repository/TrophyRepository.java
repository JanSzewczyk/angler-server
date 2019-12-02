package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Trophy;

@Repository
public interface TrophyRepository extends JpaRepository<Trophy, Long> {

}

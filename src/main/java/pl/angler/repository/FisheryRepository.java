package pl.angler.repository;

import com.sun.xml.bind.v2.TODO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Fishery;

import java.util.List;

@Repository
public interface FisheryRepository extends JpaRepository<Fishery, Long> {

    // TODO zrobić dla znajomych jak już zostanie to zaimplementowane
    List<Fishery> findDistinctByUser_emailOrPrivateFisheryFalse(String email);
}

package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmailNot(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByNick(String nick);
    Boolean existsByEmail(String email);
    Boolean existsByNick(String nick);
    Boolean existsByEmailAndNick(String email, String nick);
}

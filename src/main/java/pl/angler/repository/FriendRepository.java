package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.angler.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}

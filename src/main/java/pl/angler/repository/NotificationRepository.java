package pl.angler.repository;

import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Notification;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUser_EmailOrderByReleaseDateDescReleaseTimeDesc(String email);

    Optional<Notification> findByIdAndUser_EmailAndLookedFalse(Long id, String email);
    Optional<Notification> findByIdAndUser_Email(Long id, String email);
}

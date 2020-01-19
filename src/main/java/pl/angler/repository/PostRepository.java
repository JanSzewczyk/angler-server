package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.angler.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_NickOrderByReleaseDateDescReleaseTimeDesc(String nick);
    List<Post> findAllByUser_NickInOrUser_EmailOrderByReleaseDateDescReleaseTimeDesc(List<String> friends,String nick);
    Optional<Post> findByIdAndUser_Email(Long id, String email);
}

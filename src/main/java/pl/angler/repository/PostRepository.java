package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.angler.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_NickOrderByReleaseDateDescReleaseTimeDesc(String nick);
    List<Post> findAllByUser_NickInOrUser_EmailOrderByReleaseDateDescReleaseTimeDesc(List<String> friends,String nick);
}

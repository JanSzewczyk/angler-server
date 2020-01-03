package pl.angler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.angler.entity.Friend;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUser_nick(String nick);
    List<Friend> findAllByInvitedUser_nick(String nick);
    List<Friend> findAllByUser_emailOrInvitedUser_email(String email, String email1);
    List<Friend> findAllByInvitedUser_nickAndAcceptedTrueOrUser_nickAndAcceptedTrue(String nick, String nick1);

    Optional<Friend> findByUser_emailAndInvitedUser_nickOrUser_nickAndInvitedUser_email(String email, String nick, String nick1, String email1);

    Boolean existsByUser_emailAndInvitedUser_nickAndAcceptedTrueOrUser_nickAndInvitedUser_emailAndAcceptedTrue(String email, String nick, String nick1, String email1);
    Boolean existsByUser_emailAndInvitedUser_nickOrUser_nickAndInvitedUser_email(String email, String nick, String nick1, String email1);
    Boolean existsByAcceptedFalseAndUser_emailAndInvitedUser_nick(String email, String nick);
    Boolean existsByAcceptedFalseAndUser_nickAndInvitedUser_email(String nick, String email);
    Boolean existsByIdAndUser_emailOrInvitedUser_email(Long id, String email, String email1);
}

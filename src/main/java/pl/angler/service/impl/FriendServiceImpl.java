package pl.angler.service.impl;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.FriendDto;
import pl.angler.entity.Friend;
import pl.angler.entity.User;
import pl.angler.exception.AlreadyExistsException;
import pl.angler.exception.ConflictException;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.FriendRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.FriendService;
import pl.angler.service.NotificationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<FriendDto> getUserFriends(String nick, String userEmail) {
        List<FriendDto> friends = new ArrayList<>();

        List<Friend> friendsList = this.friendRepository.findAllByUser_nick(nick);
        friendsList.addAll(this.friendRepository.findAllByInvitedUser_nick(nick));

        if (this.userRepository.existsByEmailAndNick(userEmail, nick)) {
            friends = this.processUserFriends(friendsList, userEmail, nick);
        }
        else {
            friends = this.processUserFriends(this.friendRepository.findAllByInvitedUser_nickAndAcceptedTrueOrUser_nickAndAcceptedTrue(nick, nick), userEmail, nick);
        }

        return friends;
    }

    private List<FriendDto> processUserFriends(List<Friend> friends, String email, String nick) {
        return friends
                .stream()
                .map(friend -> {
                    FriendDto friendInfo = new FriendDto();
                    String friendNick ;
                    String friendEmail ;

                    friendInfo.setId(friend.getId());
                    friendInfo.setDate(friend.getDate());

                    if (friend.getUser().getNick().equals(nick)) {
                        friendNick = friend.getInvitedUser().getNick();
                        friendEmail = friend.getInvitedUser().getEmail();
                        friendInfo.setUserId(friend.getInvitedUser().getId());
                    } else {
                        friendNick = friend.getUser().getNick();
                        friendEmail = friend.getUser().getEmail();
                        friendInfo.setUserId(friend.getUser().getId());
                    }

                    friendInfo.setUserNick(friendNick);

                    Optional<Friend> checkFriendship = this.friendRepository.findByUser_emailAndInvitedUser_nickOrUser_nickAndInvitedUser_email(email, friendNick, friendNick, email);
                    if (checkFriendship.isPresent() ) {
                        Friend friendship = checkFriendship.get();
                        if(friendship.getAccepted()) {
                            friendInfo.setStatus(1);
                        } else if (friendship.getUser().getEmail().equals(email) && friendship.getInvitedUser().getNick().equals(friendNick) && !friendship.getAccepted()){
                            friendInfo.setStatus(2);
                        } else {
                            friendInfo.setStatus(3);
                        }
                    }  else {
                        if (friendEmail.equals(email)){
                            friendInfo.setStatus(0);
                        } else {
                            friendInfo.setStatus(4);
                        }
                    }
                    return friendInfo;
                }).collect(Collectors.toList());
    }

    @Override
    public List<FriendDto> getOnlyUserFriends(String userEmail) {
        return this.friendRepository
                .findAllByUser_emailAndAcceptedTrueOrInvitedUser_emailAndAcceptedTrue(userEmail, userEmail)
                .stream()
                .map(friend -> {
                    FriendDto friendInfo = new FriendDto();
                    friendInfo.setId(friend.getId());
                    friendInfo.setDate(friend.getDate());
                    friendInfo.setStatus(1);

                    if (friend.getUser().getEmail().equals(userEmail)) {
                        friendInfo.setUserNick(friend.getInvitedUser().getNick());
                        friendInfo.setUserId(friend.getInvitedUser().getId());
                    } else {
                        friendInfo.setUserNick(friend.getUser().getNick());
                        friendInfo.setUserId(friend.getUser().getId());
                    }
                    return friendInfo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendDto> getUnknownUsers(String userEmail) {
        List<User> friends = this.friendRepository
                .findAllByUser_emailOrInvitedUser_email(userEmail, userEmail)
                .stream()
                .map(friend -> {
                    return friend.getUser().getEmail().equals(userEmail) ? friend.getInvitedUser() : friend.getUser();
                }).collect(Collectors.toList());

        List<User> unknownUsers = this.userRepository.findAllByEmailNot(userEmail);
        unknownUsers.removeAll(friends);
        return unknownUsers.stream().map(user -> {
            FriendDto unknownUser = new FriendDto();
            unknownUser.setId(user.getId());
            unknownUser.setStatus(4);
            unknownUser.setUserId(user.getId());
            unknownUser.setUserNick(user.getNick());
            return unknownUser;
        }).collect(Collectors.toList());
    }

    @Override
    public void inviteToUserFriend(String nick, String userEmail) {
        if (this.friendRepository.existsByUser_emailAndInvitedUser_nickOrUser_nickAndInvitedUser_email( userEmail,  nick,  nick,  userEmail))
            throw new AlreadyExistsException("This user has already been invited.");

        Friend newFriend = new Friend();

        Optional<User> findUser = this.userRepository.findByEmail(userEmail);
        if (!findUser.isPresent())
            throw new NotFoundException("User with mail [" + userEmail + "] not exists.");
        User user = findUser.get();

        Optional<User> findInviteUser = this.userRepository.findByNick(nick);
        if (!findInviteUser.isPresent())
            throw new NotFoundException("User with nick [" + nick + "] not exists.");
        User inviteUser = findInviteUser.get();

        newFriend.setUser(user);
        newFriend.setInvitedUser(inviteUser);
        newFriend.setAccepted(false);
        newFriend.setDate(LocalDate.now());

        this.friendRepository.save(newFriend);

        this.notificationService.sendNotificationToUser(user.getNick() + " invited you to friends.", nick);
    }

    @Override
    public void acceptInvitationToFriends(Long id, String userEmail) {
        Optional<Friend> findFriend = this.friendRepository.findById(id);
        if (!findFriend.isPresent())
            throw new NotFoundException("Invitation not found.");

        Friend friend = findFriend.get();

        if (!friend.getInvitedUser().getEmail().equals(userEmail))
            throw new ConflictException("Invalid invitation.");

        friend.setDate(LocalDate.now());
        friend.setAccepted(true);

        this.friendRepository.save(friend);

        this.notificationService.sendNotificationToUser(friend.getInvitedUser().getNick() + " has accepted your invitation to your friends.",friend.getUser().getNick());
    }

    @Override
    public void deleteUserFromFriends(Long id, String userEmail) {
        String notificationMessage = "";

        if (!this.friendRepository.existsByIdAndUser_emailOrInvitedUser_email(id, userEmail, userEmail))
            throw new ConflictException("Incorrect data.");

        Optional<Friend> findFriend = this.friendRepository.findById(id);
        if (!findFriend.isPresent())
            throw new NotFoundException("Invitation not found.");

        Friend friend = findFriend.get();
        this.friendRepository.delete(friend);

        String fromUserNick = !friend.getUser().getEmail().equals(userEmail) ? friend.getInvitedUser().getNick() : friend.getUser().getNick();
        String toUserNick = friend.getUser().getEmail().equals(userEmail) ? friend.getInvitedUser().getNick() : friend.getUser().getNick();

        if (friend.getAccepted()) {
            notificationMessage = fromUserNick + " has removed you from friends.";
            this.notificationService.sendNotificationToUser(notificationMessage, toUserNick);
        } else if (friend.getInvitedUser().getEmail().equals(userEmail)){
            notificationMessage = fromUserNick + " rejected your invitation to your friends.";
            this.notificationService.sendNotificationToUser(notificationMessage, toUserNick);
        }
    }
}

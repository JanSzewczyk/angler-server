package pl.angler.service;

import pl.angler.dto.FriendDto;

import java.util.List;

public interface FriendService {
    List<FriendDto> getUnknownUsers(String userEmail);
    List<FriendDto> getUserFriends(String nick, String userEmail);
    void inviteToUserFriend(String nick, String userEmail);
    void acceptInvitationToFriends(Long id, String userEmail);
    void deleteUserFromFriends(Long id, String userEmail);
}

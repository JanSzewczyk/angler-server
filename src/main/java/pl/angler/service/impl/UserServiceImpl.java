package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.UserInformationDto;
import pl.angler.dto.UserProfileDto;
import pl.angler.entity.Role;
import pl.angler.entity.User;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.FriendRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.FisheryService;
import pl.angler.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private FisheryService fisheryService;

    @Override
    public UserInformationDto getUserInformation(String email) {
        Optional<User> findUser = this.userRepository.findByEmail(email);
        if (!findUser.isPresent()) {
            throw new NotFoundException("User with mail [" + email + "] not exists.");
        }
        User user = findUser.get();

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        return  new UserInformationDto(user.getNick(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthDate(), roles);
    }

    private UserInformationDto getUserInformationByNick(String nick) {
        Optional<User> findUser = this.userRepository.findByNick(nick);
        if (!findUser.isPresent()) {
            throw new NotFoundException("User with nick [" + nick + "] not exists.");
        }
        User user = findUser.get();

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        return  new UserInformationDto(user.getNick(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthDate(), roles);
    }

    @Override
    public UserProfileDto getUserProfile(String nick, String userMail) {
        UserProfileDto userProfile = new UserProfileDto();
        if (this.userRepository.existsByEmailAndNick(userMail, nick)) {
            userProfile.setStatus(0);
            userProfile.setUser(this.getUserInformation(userMail));
            userProfile.setFisheries(this.fisheryService.getAllUserFisheries(userMail));
        } else if (this.friendRepository.existsByUser_emailAndInvitedUser_nickAndAcceptedTrueOrUser_nickAndInvitedUser_emailAndAcceptedTrue(userMail, nick, nick, userMail)) {
            userProfile.setStatus(1);
            userProfile.setUser(this.getUserInformationByNick(nick));
            userProfile.setFisheries(this.fisheryService.getAllPublicFisheries(nick));
        } else if (this.friendRepository.existsByAcceptedFalseAndUser_emailAndInvitedUser_nick(userMail,  nick)) {
            userProfile.setStatus(2);
            userProfile.setUser(this.getUserInformationByNick(nick));
        } else if (this.friendRepository.existsByAcceptedFalseAndUser_nickAndInvitedUser_email(nick, userMail)) {
            userProfile.setStatus(3);
            userProfile.setUser(this.getUserInformationByNick(nick));
        } else {
            userProfile.setStatus(4);
            userProfile.setUser(this.getUserInformationByNick(nick));
        }

        return userProfile;
    }
}

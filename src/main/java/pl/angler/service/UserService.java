package pl.angler.service;

import pl.angler.dto.UserInformationDto;
import pl.angler.dto.UserProfileDto;

public interface UserService {
    UserInformationDto getUserInformation(String email);
    UserProfileDto getUserProfile(String nick, String userMail);
}

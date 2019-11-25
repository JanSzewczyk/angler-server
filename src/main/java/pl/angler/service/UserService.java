package pl.angler.service;

import pl.angler.dto.UserInformationDto;

public interface UserService {
    UserInformationDto getUserInformation(String email);
}

package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.UserInformationDto;
import pl.angler.entity.Role;
import pl.angler.entity.User;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.UserRepository;
import pl.angler.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

        return  new UserInformationDto(user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthDate(), roles);
    }
}

package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.angler.entity.Role;
import pl.angler.entity.User;
import pl.angler.exception.AlreadyExistsException;
import pl.angler.repository.RoleRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.SignUpService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createNewUser(User newUser) {

        if(userRepository.existsByEmail(newUser.getEmail())) {
            throw new AlreadyExistsException("The user with this email already exists.");
        }

        Optional<Role> userRole = this.roleRepository.findById(Long.valueOf(1));
        System.out.println(userRole.get());

        // Add role
        List<Role> roles = new ArrayList<>();
        roles.add(userRole.get());

        // Password encode
        String encoded = new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(encoded);

        newUser.setRoles(roles);
        this.userRepository.save(newUser);

        System.out.println(newUser);
    }
}

package pl.angler.service.impl;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.angler.entity.Role;
import pl.angler.entity.User;
import pl.angler.exception.AlreadyExistsException;
import pl.angler.exception.NotFoundException;
import pl.angler.exception.ServerException;
import pl.angler.repository.RoleRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.EmailSenderService;
import pl.angler.service.SignUpService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createNewUser(User newUser) {

        if(this.userRepository.existsByEmail(newUser.getEmail())) {
            throw new AlreadyExistsException("The user with this email already exists.");
        }

        Optional<Role> userRole = this.roleRepository.findById(1L);
        if(!userRole.isPresent()) {
            throw new NotFoundException("The server has trouble granting rights to the user.");
        }

        // Add role
        List<Role> roles = new ArrayList<>();
        roles.add(userRole.get());

        // Password encode
        String encoded = new BCryptPasswordEncoder().encode(newUser.getPassword());
        newUser.setPassword(encoded);
        newUser.setRoles(roles);
        newUser.setAuthenticated(false);

        this.userRepository.save(newUser);

        // Send Confirmation Mail
        try {
            this.emailSenderService.sendConfirmationMail(newUser.getFirstName(), newUser.getEmail());
        } catch (MessagingException | IOException | TemplateException e) {
            this.userRepository.deleteById(newUser.getId());
            throw new ServerException("Problem with mail sending. Sorry.");
        }
    }

    @Override
    public void confirmNewUser(String userEmail) {
        Optional<User> findUser = this.userRepository.findByEmail(userEmail);

        if (!findUser.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }

        User user = findUser.get();
        user.setAuthenticated(true);

        this.userRepository.save(user);
    }
}

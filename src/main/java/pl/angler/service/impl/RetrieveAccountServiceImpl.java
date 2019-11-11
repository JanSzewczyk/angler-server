package pl.angler.service.impl;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.angler.dto.RetrieveAccountDto;
import pl.angler.entity.User;
import pl.angler.exception.ConflictException;
import pl.angler.exception.NotFoundException;
import pl.angler.exception.ServerException;
import pl.angler.repository.UserRepository;
import pl.angler.service.EmailSenderService;
import pl.angler.service.RetrieveAccountService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@Service
public class RetrieveAccountServiceImpl implements RetrieveAccountService {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void retrievePasswordMailSender(String email) {
        Optional<User> findUser = this.userRepository.findByEmail(email);
        if (!findUser.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }

        User user = findUser.get();
        try {
            this.emailSenderService.sendRetrieveMail(user.getFirstName(), user.getEmail());
        } catch (MessagingException | IOException | TemplateException e) {
            throw new ServerException("Problem with mail sending. Sorry, try later.");
        };
    }

    @Override
    public void retrievePassword(RetrieveAccountDto retrieveAccount) {
        Optional<User> user = this.userRepository.findByEmail(retrieveAccount.getEmail());
        if (!user.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }
        User updatingUser = user.get();

        if (bCryptPasswordEncoder.matches(retrieveAccount.getNewPassword(), updatingUser.getPassword())) {
            throw new ConflictException("This password is the same as the current one, please come up with a different one.");
        }

        updatingUser.setPassword(bCryptPasswordEncoder.encode(retrieveAccount.getNewPassword()));
        this.userRepository.save(updatingUser);
    }
}

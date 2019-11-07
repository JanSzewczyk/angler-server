package pl.angler.controller;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.entity.User;
import pl.angler.exception.ServerException;
import pl.angler.model.ErrorModel;
import pl.angler.service.EmailSenderService;
import pl.angler.service.SignUpService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;


@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("")
    public ResponseEntity createUser(@Valid @RequestBody User newUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ErrorModel(bindingResult));
        }

        this.signUpService.createNewUser(newUser);

        try {
            this.emailSenderService.sendConfirmationMail(newUser.getId(), newUser.getFirstName() + " " + newUser, newUser.getEmail());
        } catch (MessagingException | IOException | TemplateException e) {

            throw new ServerException("Problem with mail sending. Sorry :(");
        }

        URI location = URI.create(String.format("/persons/%s", newUser.getEmail()));
        return ResponseEntity.created(location).build();
    }

//    @PostMapping("/confirm")
//    public ResponseEntity confirmUser() {
//
//        return ResponseEntity.ok();
//    }

}

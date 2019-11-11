package pl.angler.controller;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("")
    public ResponseEntity createUser(@Valid @RequestBody User newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(new ErrorModel(bindingResult));
        }

        this.signUpService.createNewUser(newUser);

        URI location = URI.create(String.format("/persons/%s", newUser.getEmail()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/confirm")
    public ResponseEntity<Void> confirmUser(@RequestParam(name = "email", required = true) String email) {
        this.signUpService.confirmNewUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
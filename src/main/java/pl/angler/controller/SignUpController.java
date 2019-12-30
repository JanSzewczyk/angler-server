package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.angler.entity.User;
import pl.angler.util.ErrorModel;
import pl.angler.service.SignUpService;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
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
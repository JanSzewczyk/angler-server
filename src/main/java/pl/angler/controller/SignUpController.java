package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.entity.User;
import pl.angler.model.ErrorModel;
import pl.angler.service.SignUpService;

import javax.validation.Valid;
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

        System.out.println("Created user: " + newUser);
        URI location = URI.create(String.format("/persons/%s", newUser.getEmail()));
        return ResponseEntity.created(location).build();
    }


}

package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.dto.UserInformationDto;
import pl.angler.dto.UserProfileDto;
import pl.angler.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<UserInformationDto> getUserInfo(final Principal principal){
        return new ResponseEntity<>(this.userService.getUserInformation(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/{nick}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<UserProfileDto> getUserProfile(final Principal principal, @PathVariable("nick") String nick ){
        return new ResponseEntity<>(this.userService.getUserProfile(nick, principal.getName()), HttpStatus.OK);
    }
}

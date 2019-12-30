package pl.angler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.FishingTripDto;
import pl.angler.dto.FriendDto;
import pl.angler.entity.Friend;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<FriendDto>> getUserFriends(final Principal principal) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

//    @GetMapping
//    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
//    public ResponseEntity<List<FriendDto>> getUserFriends(final Principal principal) {
//
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> deleteFriend(final Principal principal,@PathVariable("id") Long id) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

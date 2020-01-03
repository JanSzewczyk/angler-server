package pl.angler.controller;

import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.FriendDto;
import pl.angler.service.FriendService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/{nick}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<FriendDto>> getUserFriends(final Principal principal, @PathVariable("nick") String nick ) {
        return new ResponseEntity<>(this.friendService.getUserFriends(nick, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/unknown")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<FriendDto>> getUnknownUsers(final Principal principal) {
        return new ResponseEntity<>(this.friendService.getUnknownUsers(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/{nick}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> inviteToFriends(final Principal principal, @PathVariable("nick") String nick) {
        this.friendService.inviteToUserFriend(nick, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/accept/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> acceptInvitation(final Principal principal, @PathVariable("id") Long id) {
        this.friendService.acceptInvitationToFriends(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> deleteFriend(final Principal principal, @PathVariable("id") Long id) {
        this.friendService.deleteUserFromFriends(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/decline/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> declineInvitation(final Principal principal, @PathVariable("id") Long id) {
        this.friendService.deleteUserFromFriends(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.NotificationDto;
import pl.angler.service.NotificationService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<NotificationDto>> getNotification(final Principal principal) {
        return new ResponseEntity<>(this.notificationService.getUserNotification(principal.getName()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> readNotification(final Principal principal, @PathVariable("id") Long id) {
        this.notificationService.readUserNotification(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> removeNotification(final Principal principal, @PathVariable("id") Long id) {
        this.notificationService.removeUserNotification(id, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

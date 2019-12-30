package pl.angler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.FishingTripDto;
import pl.angler.entity.FishingTrip;
import pl.angler.service.FishingTripService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/trip")
public class FishingTripController {

    @Autowired
    private FishingTripService fishingTripService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<FishingTripDto>> getTrips(final Principal principal) {
        log.info("User with email: [" + principal.getName() + "] gets fishing trips.");
        return new ResponseEntity<>(this.fishingTripService.getTrips(principal.getName()), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> createNewFishingTrip(final Principal principal, @RequestBody FishingTripDto newFishingTrip) {

        this.fishingTripService.saveNewFishingTrip(principal.getName(), newFishingTrip);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<FishingTrip> getTripByID(final Principal principal, @PathVariable("id") Long id) {

        return new ResponseEntity<>(this.fishingTripService.findTripById(principal.getName(), id) , HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> updateFishingTrip(final Principal principal, @RequestBody FishingTripDto updateFishingTrip) {
        this.fishingTripService.updateFishingTrip(principal.getName(), updateFishingTrip);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> removeFishingTrip(@PathVariable("id") Long id) {
        this.fishingTripService.removeFishingTrip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

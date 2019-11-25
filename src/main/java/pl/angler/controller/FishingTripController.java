package pl.angler.controller;

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

@RestController
@RequestMapping("/trip")
public class FishingTripController {

    @Autowired
    private FishingTripService fishingTripService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<FishingTripDto>> getTrips(final Principal principal) {

        return new ResponseEntity<>(this.fishingTripService.getTrips(principal.getName()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewFishingTrip(
            final Principal principal,
            @RequestBody FishingTrip newFishingTrip
            ) {

        System.out.println(newFishingTrip);
        this.fishingTripService.saveNewFishingTrip(principal.getName(), newFishingTrip);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

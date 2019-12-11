package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.TrophyDto;
import pl.angler.service.TrophyService;

@RestController
@RequestMapping("/trophy")
public class TrophyController {

    @Autowired
    private TrophyService trophyService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> createNew(@RequestBody TrophyDto trophyDto) {
        this.trophyService.addNewTrophy(trophyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> editTrophy(@RequestBody TrophyDto trophyDto) {
        this.trophyService.updateTrophy(trophyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> deleteTrophy(@PathVariable("id") Long id) {
        this.trophyService.deleteTrophy(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.dto.FisheryDto;
import pl.angler.entity.Fish;
import pl.angler.entity.Fishery;
import pl.angler.service.FisheryService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/fishery")
public class FisheryController {

    @Autowired
    private FisheryService fisheryService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<FisheryDto>> getFisheries(final Principal principal) {

        return new ResponseEntity<>(this.fisheryService.getAllUserFisheries(principal.getName()), HttpStatus.OK);
    }
}

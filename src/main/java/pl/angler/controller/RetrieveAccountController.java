package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.RetrieveAccountDto;
import pl.angler.service.RetrieveAccountService;

@RestController
@RequestMapping("/retrieve")
public class RetrieveAccountController {

    private final RetrieveAccountService retrieveAccountService;

    @Autowired
    public RetrieveAccountController(RetrieveAccountService retrieveAccountService) {
        this.retrieveAccountService = retrieveAccountService;
    }

    @PostMapping
    public ResponseEntity<Void> retrieveAccount(@RequestParam(name = "email", required = true) String email){
        this.retrieveAccountService.retrievePasswordMailSender(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> changePassword(@RequestBody RetrieveAccountDto retAcc){
        this.retrieveAccountService.retrievePassword(retAcc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

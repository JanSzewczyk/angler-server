package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.entity.Lake;
import pl.angler.service.LakeService;

import java.util.List;

@RestController
@RequestMapping("/lake")
public class LakeController {

    @Autowired
    private LakeService lakeService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<Lake>> getAllLakes() {
        return new ResponseEntity<>(this.lakeService.getAllLakes(), HttpStatus.OK);
    }
}

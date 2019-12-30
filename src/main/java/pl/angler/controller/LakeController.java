package pl.angler.controller;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/lake")
public class LakeController {

    @Autowired
    private LakeService lakeService;

    @GetMapping
    public ResponseEntity<List<Lake>> getAllLakes() {

        log.info("Getting lakes.");
        return new ResponseEntity<>(this.lakeService.getAllLakes(), HttpStatus.OK);
    }
}

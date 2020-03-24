package pl.angler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.dto.NotificationDto;
import pl.angler.dto.StatisticsDto;
import pl.angler.service.StatisticService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<StatisticsDto> getNotification(final Principal principal) {
        StatisticsDto statistics = this.statisticService.getUserStatistics(principal.getName());
        log.info("USER [email: " + principal.getName() + " gets statistics");
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

}

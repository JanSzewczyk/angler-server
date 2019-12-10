package pl.angler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnglerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnglerApplication.class, args);
    }
}

package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long id;
    private LocalDate releaseDate;
    private LocalTime releaseTime;
    private Boolean looked;
    private String message;
}

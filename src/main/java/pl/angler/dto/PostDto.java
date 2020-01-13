package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String userNick;
    private Integer status;
    private String description;
    private LocalDate releaseDate;
    private LocalTime releaseTime;
    private FishingTripDto fishingTrip;
    private FisheryDto fishery;
}

package pl.angler.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TrophyDto {

    private Long id;
    private Long fishId;
    private Long tripId;
    private Double length;
    private Double weight;
    private LocalTime time;
}

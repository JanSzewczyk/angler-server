package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrophyDto {
    private Long id;
    private Long fishId;
    private Long tripId;
    private Double length;
    private Double weight;
    private LocalTime time;
}

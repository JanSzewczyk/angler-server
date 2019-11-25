package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.angler.entity.Fishery;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishingTripDto {
    private Long id;
    private String title;
    private LocalDate tripDate;
    private String description;
    private Fishery fishery;
}

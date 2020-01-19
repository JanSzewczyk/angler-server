package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.angler.entity.Trophy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishingTripDto {
    private Long id;
    private String title;
    private LocalDate tripDate;
    private String description;
    private FisheryDto fishery;
    private List<Trophy> trophies = new ArrayList<>();
    private Boolean share;
}





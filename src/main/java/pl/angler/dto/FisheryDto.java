package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FisheryDto {
    private Long id;
    private String name;
    private Double altitude;
    private Double latitude;
    private String description;
    private Boolean share;
}

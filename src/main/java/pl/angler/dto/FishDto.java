package pl.angler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishDto {
    private Long id;
    private String name;
    private String family;
    private String look;
    private String photo;
    private String occurrence;
    private String timeToFishing;
    private String bait;
    private String groundbait;
    private String methodAndTechnique;
    private String equipment;
    private String fishery;
    private String periodOfProtection;
    private String protectiveSize;
    private String record;
}

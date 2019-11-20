package pl.angler.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="fishery")
public class Fishery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", length = 256, unique = true, nullable = false)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @NotNull
    @Column(name = "altitude")
    private Double altitude; //wysokość

    @NotNull
    @Column(name = "latitude")
    private Double latitude; //szerokość

    @OneToMany(
            mappedBy = "fishery",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<FishingTrip> fishingTrip;
}

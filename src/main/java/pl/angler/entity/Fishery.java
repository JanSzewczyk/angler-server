package pl.angler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NotNull
    @Column(name = "altitude")
    private Double altitude; //dlugość 19

    @NotNull
    @Column(name = "latitude")
    private Double latitude; //szerokość 51

    @OneToMany(
            mappedBy = "fishery",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<FishingTrip> fishingTrip;
}

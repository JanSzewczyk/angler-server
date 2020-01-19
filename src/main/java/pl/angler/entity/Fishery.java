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
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(name = "altitude")
    private Double altitude;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "privateFishery")
    private Boolean privateFishery = true;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "fishery",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<FishingTrip> fishingTrip;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToOne(
            mappedBy = "fishery",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private Post post;
}

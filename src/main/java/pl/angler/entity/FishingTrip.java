package pl.angler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="fishing_trip")
public class FishingTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "title", length = 256)
    private String title;

    @NotNull
    @Column(name = "trip_date")
    private LocalDate tripDate;

    @Column(name = "description", length = 256)
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "fishery_id")
    private Fishery fishery;

   @OneToMany(
           mappedBy = "fishingTrip",
           fetch = FetchType.LAZY,
           cascade = {CascadeType.ALL}
   )
    private List<Trophy> trophies;

    @OneToOne(
            mappedBy = "fishingTrip",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Post post;
}




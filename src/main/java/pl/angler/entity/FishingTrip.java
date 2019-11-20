package pl.angler.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="fishing_trip")
public class FishingTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "description", length = 256)
    private String description;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "fishery_id")
    private Fishery fishery;

   @OneToMany(
           mappedBy = "fishingTrip",
           fetch = FetchType.LAZY,
           cascade = {CascadeType.ALL}
   )
    private List<Trophy> trophies;

}

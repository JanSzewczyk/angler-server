package pl.angler.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="trophy")
public class Trophy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "fish_id")
    private Fish fish;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "fishing_trip_id")
    private FishingTrip fishingTrip;

    @Column(name = "size")
    private Double size;

    @Column(name = "weight")
    private Double weight;
}

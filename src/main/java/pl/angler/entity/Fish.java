package pl.angler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="fish")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @UniqueElements
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "family")
    private String family;

    @Column(name = "look", length = 4096)
    private String look;

    @Column(name = "photo")
    private String photo;

    @Column(name = "occurrence", length = 4096)
    private String occurrence;

    @Column(name = "time_to_fishing", length = 4096)
    private String timeToFishing;

    @Column(name = "bait", length = 512)
    private String bait;

    @Column(name = "groundbait", length = 512)
    private String groundbait;

    @Column(name = "method_and_technique", length = 4096)
    private String methodAndTechnique;

    @Column(name = "equipment", length = 4096)
    private String equipment;

    @Column(name = "fishery", length = 512)
    private String fishery;

    @Column(name = "period_of_protection")
    private String periodOfProtection;

    @Column(name = "protective_size")
    private String protectiveSize;

    @Column(name = "record")
    private String record;

    @OneToMany(
            mappedBy = "fish",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JsonIgnore
    private List<Trophy> trophies;
}

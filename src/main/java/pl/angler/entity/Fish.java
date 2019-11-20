package pl.angler.entity;

import lombok.Data;
import org.hibernate.validator.constraints.EAN;
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

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "fish",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    private List<Trophy> trophies;
}

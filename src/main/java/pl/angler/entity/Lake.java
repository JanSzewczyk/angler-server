package pl.angler.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="lake")
public class Lake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "altitude")
    private Double altitude; //wysokość

    @Column(name = "latitude")
    private Double latitude; //szerokość
}
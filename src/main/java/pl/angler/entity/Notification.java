package pl.angler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @NotNull
    @Column(name = "release_time")
    private LocalTime releaseTime;

    @Column(name = "looked")
    private Boolean looked = false;

    @Column(name = "message")
    private String message;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JoinColumn(name = "user_id")
    private User user;
}

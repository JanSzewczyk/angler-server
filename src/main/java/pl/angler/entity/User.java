package pl.angler.entity;

import lombok.Data;
import pl.angler.validation.EmailValidator;
import pl.angler.validation.PasswordValidator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @EmailValidator
    @Column(name = "email", unique = true)
    private String email;

    @PasswordValidator
    @Column(name = "password")
//    @JsonIgnore
    private String password;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "authenticated")
    private boolean authenticated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,
                                                    CascadeType.MERGE,
                                                    CascadeType.PERSIST,
                                                    CascadeType.REFRESH})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<FishingTrip> fishingTrip;

    public User() {
    }

    public User(@NotNull String email, @NotNull String password, @NotNull String firstName, @NotNull String lastName, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

}


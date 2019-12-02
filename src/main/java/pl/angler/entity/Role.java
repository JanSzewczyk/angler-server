package pl.angler.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name="role")
public class Role {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="role_name", unique = true)
    private String roleName;

    @Column(name="description")
    private String description;

    public Role() {
    }

    public Role(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }
}

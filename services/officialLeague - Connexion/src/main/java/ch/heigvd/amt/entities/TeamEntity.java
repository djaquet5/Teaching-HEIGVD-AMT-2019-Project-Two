package ch.heigvd.amt.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

// TODO
@Getter
@Entity
@Table(name = "Team")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String name;

    @Setter
    private String address;

    @Setter
    private String zip;

    @Setter
    private String city;
}

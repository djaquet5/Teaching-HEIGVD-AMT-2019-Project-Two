package ch.heigvd.amt.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

// TODO
@Getter
@Entity
@Table(name = "Team")
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;

    @Setter
    private String address;

    @Setter
    private String zip;

    @Setter
    private String city;
}

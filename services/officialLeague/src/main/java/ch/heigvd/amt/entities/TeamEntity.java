package ch.heigvd.amt.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

// TODO
@Getter
@Setter
@Entity
@Table(name = "Team")
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String address;
    private String zip;
    private String city;
}

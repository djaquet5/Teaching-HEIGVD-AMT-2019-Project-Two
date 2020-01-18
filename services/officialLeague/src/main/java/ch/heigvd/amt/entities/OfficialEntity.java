package ch.heigvd.amt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Official")
public class OfficialEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int level;

    @ManyToOne
    @JoinColumn(name = "idTeam")
    private TeamEntity team;
}

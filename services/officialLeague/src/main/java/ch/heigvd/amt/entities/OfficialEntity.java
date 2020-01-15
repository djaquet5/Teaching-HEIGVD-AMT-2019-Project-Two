package ch.heigvd.amt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// TODO
@Getter
@Entity
@Table(name = "Official")
public class OfficialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private int level;

    @ManyToOne
    @JoinColumn(name = "idTeam")
    @Setter
    private TeamEntity team;
}

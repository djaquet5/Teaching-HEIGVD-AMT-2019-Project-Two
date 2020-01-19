package ch.heigvd.amt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Game")
public class GameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "idTeamAway")
    private TeamEntity away;

    @ManyToOne
    @JoinColumn(name = "idTeamHome")
    private TeamEntity home;

    @ManyToOne
    @JoinColumn(name = "idReferee")
    private OfficialEntity referee;

    @ManyToOne
    @JoinColumn(name = "idUmpire")
    private OfficialEntity umpire;

    @ManyToOne
    @JoinColumn(name = "idChainJudge")
    private OfficialEntity chainJudge;

    @ManyToOne
    @JoinColumn(name = "idLineJudge")
    private OfficialEntity lineJudge;

    @ManyToOne
    @JoinColumn(name = "idBackJudge")
    private OfficialEntity backJudge;

    @ManyToOne
    @JoinColumn(name = "idSideJudge")
    private OfficialEntity sideJudge;

    @ManyToOne
    @JoinColumn(name = "idFieldJudge")
    private OfficialEntity fieldJudge;
}

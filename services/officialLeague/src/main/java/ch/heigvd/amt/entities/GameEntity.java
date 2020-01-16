package ch.heigvd.amt.entities;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

// TODO
@Getter
@Entity
@Table(name = "Game")
public class GameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "DATETIME")
    @Setter
    private DateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "idTeamAway")
    @Setter
    private TeamEntity away;

    @ManyToOne
    @JoinColumn(name = "idTeamHome")
    @Setter
    private TeamEntity home;

    @ManyToOne
    @JoinColumn(name = "idReferee")
    @Setter
    private OfficialEntity referee;

    @ManyToOne
    @JoinColumn(name = "idUmpire")
    @Setter
    private OfficialEntity umpire;

    @ManyToOne
    @JoinColumn(name = "idChainJudge")
    @Setter
    private OfficialEntity chainJudge;

    @ManyToOne
    @JoinColumn(name = "idLineJudge")
    @Setter
    private OfficialEntity lineJudge;

    @ManyToOne
    @JoinColumn(name = "idBackJudge")
    @Setter
    private OfficialEntity backJudge;

    @ManyToOne
    @JoinColumn(name = "idSideJudge")
    @Setter
    private OfficialEntity sideJudge;

    @ManyToOne
    @JoinColumn(name = "idFieldJudge")
    @Setter
    private OfficialEntity fieldJudge;
}

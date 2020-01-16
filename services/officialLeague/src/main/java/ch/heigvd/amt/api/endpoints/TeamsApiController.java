package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.TeamsApi;
import ch.heigvd.amt.api.model.Team;
import ch.heigvd.amt.entities.TeamEntity;
import ch.heigvd.amt.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamsApiController implements TeamsApi {

    @Autowired
    TeamRepository teamRepository;

    public ResponseEntity<List<Team>> getTeams(String authorization) {
        List<Team> teams = new ArrayList<>();
        for (TeamEntity teamEntity : teamRepository.findAll()) {
            teams.add(toTeam(teamEntity));
        }

        return ResponseEntity.ok(teams);
    }

    private TeamEntity toTeamEntity(Team team) {
        TeamEntity entity = new TeamEntity();
        entity.setName(entity.getName());
        entity.setAddress(team.getAddress());
        entity.setCity(team.getCity());
        entity.setZip(team.getZip());

        return entity;
    }

    public static Team toTeam(TeamEntity entity) {
        Team team = new Team();
        team.setName(entity.getName());
        team.setAddress(entity.getAddress());
        team.setZip(entity.getZip());
        team.setCity(entity.getCity());

        return team;
    }
}

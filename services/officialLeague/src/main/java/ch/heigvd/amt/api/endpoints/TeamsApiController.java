package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.TeamsApi;
import ch.heigvd.amt.api.model.Team;
import ch.heigvd.amt.entities.TeamEntity;
import ch.heigvd.amt.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeamsApiController implements TeamsApi {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public ResponseEntity<Void> createTeam(@Valid Team team) {
        TeamEntity entity = toTeamEntity(team);
        teamRepository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //    public ResponseEntity<List<Team>> getTeams(String authorization) {
    @Override
    public ResponseEntity<List<Team>> getTeams() {
        List<Team> teams = new ArrayList<>();
        for (TeamEntity teamEntity : teamRepository.findAll()) {
            teams.add(toTeam(teamEntity));
        }

        return ResponseEntity.ok(teams);
    }

    @Override
    public ResponseEntity<Team> getTeamById(Integer teamId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);

        if(teamEntity.isPresent()) {
            return ResponseEntity.ok(toTeam(teamEntity.get()));
        }

        // TODO : not working
        return ResponseEntity.notFound().build();
    }

    private TeamEntity toTeamEntity(Team team) {
        TeamEntity entity = new TeamEntity();
        entity.setName(team.getName());
        entity.setAddress(team.getAddress());
        entity.setCity(team.getCity());
        entity.setZip(team.getZip());

        return entity;
    }

    public static Team toTeam(TeamEntity entity) {
        Team team = new Team();
        team.setId(entity.getId());
        team.setName(entity.getName());
        team.setAddress(entity.getAddress());
        team.setZip(entity.getZip());
        team.setCity(entity.getCity());

        return team;
    }
}

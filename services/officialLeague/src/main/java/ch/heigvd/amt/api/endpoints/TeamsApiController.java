package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.TeamsApi;
import ch.heigvd.amt.api.model.Team;
import ch.heigvd.amt.api.model.TeamDTO;
import ch.heigvd.amt.entities.TeamEntity;
import ch.heigvd.amt.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class TeamsApiController implements TeamsApi {

    @Autowired
    TeamRepository teamRepository;

    ////////////////// CREATE //////////////////
    @Override
    public ResponseEntity<Void> createTeam(@Valid TeamDTO team) {
        TeamEntity entity = toTeamEntity(team);
        teamRepository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ////////////////// READ //////////////////
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

    ////////////////// UPDATE //////////////////
    @Override
    public ResponseEntity<Void> updateTeamById(Integer teamId, @Valid TeamDTO team) {
        TeamEntity entity;

        try {
            entity = teamRepository.findById(teamId).get();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        changeElements(entity, team);
        teamRepository.save(entity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    ////////////////// DELETE //////////////////
    @Override
    public ResponseEntity<Void> deleteTeamById(Integer teamId) {
        if (!teamRepository.existsById(teamId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        teamRepository.deleteById(teamId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private TeamEntity toTeamEntity(TeamDTO dto) {
        TeamEntity entity = new TeamEntity();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setZip(dto.getZip());

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

    private void changeElements(TeamEntity entity, TeamDTO dto) {
        String name = dto.getName();
        String address = dto.getAddress();
        String zip = dto.getZip();
        String city = dto.getCity();

        if(name != null && !name.isEmpty())
            entity.setName(name);

        if(address != null && !address.isEmpty())
            entity.setAddress(address);

        if(zip != null && !zip.isEmpty())
            entity.setZip(zip);

        if(city != null && !city.isEmpty())
            entity.setCity(city);
    }
}

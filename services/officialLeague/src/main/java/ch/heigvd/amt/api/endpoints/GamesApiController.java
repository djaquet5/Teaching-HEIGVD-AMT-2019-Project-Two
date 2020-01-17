package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.GamesApi;
import ch.heigvd.amt.api.model.Game;
import ch.heigvd.amt.api.model.GameDTO;
import ch.heigvd.amt.entities.GameEntity;
import ch.heigvd.amt.repositories.GameRepository;
import ch.heigvd.amt.repositories.OfficialRepository;
import ch.heigvd.amt.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class GamesApiController implements GamesApi {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    OfficialRepository officialRepository;

    ////////////////// CREATE //////////////////
    @Override
    public ResponseEntity<Void> createGame(@Valid GameDTO game) {
        GameEntity entity = toGameEntity(game);
        if (entity == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        gameRepository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //    public ResponseEntity<List<Game>> getGames(String authorization) {

    ////////////////// READ //////////////////
    @Override
    public ResponseEntity<List<Game>> getGames(@Min(0) @Valid Integer page, @Min(0) @Valid Integer limit) {
        List<Game> games = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, limit);
        for (GameEntity gameEntity : gameRepository.findAll(pageable)) {
            games.add(toGame(gameEntity));
        }

        return ResponseEntity.ok(games);
    }

    @Override
    public ResponseEntity<Game> getGameById(Integer gameId) {
        Optional<GameEntity> officialEntity = gameRepository.findById(gameId);

        if(officialEntity.isPresent()) {
            return ResponseEntity.ok(toGame(officialEntity.get()));
        }

        // TODO : not working
        return ResponseEntity.notFound().build();
    }

    ////////////////// UPDATE //////////////////


    ////////////////// DELETE //////////////////
    @Override
    public ResponseEntity<Void> deleteGameById(Integer gameId) {
        if(!gameRepository.existsById(gameId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        gameRepository.deleteById(gameId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private GameEntity toGameEntity(GameDTO dto) {
        GameEntity entity = new GameEntity();

        try{
            // Set the teams
            entity.setAway(teamRepository.findById(dto.getIdTeamAway()).get());
            entity.setHome(teamRepository.findById(dto.getIdTeamHome()).get());

            // Set the officials
            entity.setReferee(officialRepository.findById(dto.getIdReferee()).get());
            entity.setUmpire(officialRepository.findById(dto.getIdUmpire()).get());
            entity.setChainJudge(officialRepository.findById(dto.getIdChainJudge()).get());
            entity.setLineJudge(officialRepository.findById(dto.getIdLineJudge()).get());
            entity.setBackJudge(officialRepository.findById(dto.getIdBackJudge()).get());
            entity.setSideJudge(officialRepository.findById(dto.getIdSideJudge()).get());
            entity.setFieldJudge(officialRepository.findById(dto.getIdFieldJudge()).get());
        } catch(NoSuchElementException e) {
            System.out.println(e.getMessage());

            return null;
        }
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }

    private Game toGame(GameEntity entity) {
        Game game = new Game();
        game.setId(entity.getId());
        game.setTimestamp(entity.getTimestamp());
        game.setTeamAway(TeamsApiController.toTeam(entity.getAway()));
        game.setTeamHome(TeamsApiController.toTeam(entity.getHome()));
        game.setReferee(OfficialsApiController.toOfficial(entity.getReferee()));
        game.setUmpire(OfficialsApiController.toOfficial(entity.getUmpire()));
        game.setChainJudge(OfficialsApiController.toOfficial(entity.getChainJudge()));
        game.setLineJudge(OfficialsApiController.toOfficial(entity.getLineJudge()));
        game.setBackJudge(OfficialsApiController.toOfficial(entity.getBackJudge()));
        game.setFieldJudge(OfficialsApiController.toOfficial(entity.getFieldJudge()));
        game.setSideJudge(OfficialsApiController.toOfficial(entity.getSideJudge()));

        return game;
    }
}

package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.GamesApi;
import ch.heigvd.amt.api.model.Game;
import ch.heigvd.amt.entities.GameEntity;
import ch.heigvd.amt.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GamesApiController implements GamesApi {

    @Autowired
    GameRepository gameRepository;

//    public ResponseEntity<List<Game>> getGames(String authorization) {

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

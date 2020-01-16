package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.GamesApi;
import ch.heigvd.amt.api.model.Game;
import ch.heigvd.amt.entities.GameEntity;
import ch.heigvd.amt.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GamesApiController implements GamesApi {

    @Autowired
    GameRepository gameRepository;

    @Override
    public ResponseEntity<List<Game>> getGames(String authorization) {
        List<Game> games = new ArrayList<>();
        for (GameEntity gameEntity : gameRepository.findAll()) {
            games.add(toGame(gameEntity));
        }

        return ResponseEntity.ok(games);
    }

    private Game toGame(GameEntity entity) {
        Game game = new Game();
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

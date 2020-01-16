package ch.heigvd.amt.repositories;

import ch.heigvd.amt.entities.GameEntity;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<GameEntity, Integer> {
}

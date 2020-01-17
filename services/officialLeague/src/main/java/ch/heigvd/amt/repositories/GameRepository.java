package ch.heigvd.amt.repositories;

import ch.heigvd.amt.entities.GameEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<GameEntity, Integer> {
}

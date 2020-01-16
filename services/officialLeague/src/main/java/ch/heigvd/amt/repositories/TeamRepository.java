package ch.heigvd.amt.repositories;

import ch.heigvd.amt.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<TeamEntity, Long> {
    
}

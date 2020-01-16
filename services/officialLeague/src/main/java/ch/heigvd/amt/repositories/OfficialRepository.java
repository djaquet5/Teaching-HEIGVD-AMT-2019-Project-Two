package ch.heigvd.amt.repositories;

import ch.heigvd.amt.entities.OfficialEntity;
import org.springframework.data.repository.CrudRepository;

public interface OfficialRepository extends CrudRepository<OfficialEntity, Integer> {
}

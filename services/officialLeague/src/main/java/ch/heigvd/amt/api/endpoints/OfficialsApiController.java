package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.OfficialsApi;
import ch.heigvd.amt.api.model.Official;
import ch.heigvd.amt.api.model.OfficialDTO;
import ch.heigvd.amt.entities.OfficialEntity;
import ch.heigvd.amt.entities.TeamEntity;
import ch.heigvd.amt.repositories.OfficialRepository;
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
public class OfficialsApiController implements OfficialsApi {

    @Autowired
    OfficialRepository officialRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public ResponseEntity<Void> createOfficial(@Valid OfficialDTO official) {
        OfficialEntity entity = toOfficialEntity(official);
        if (entity == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        officialRepository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
//    public ResponseEntity<List<Official>> getOfficials(String authorization) {
    public ResponseEntity<List<Official>> getOfficials() {
        List<Official> officials = new ArrayList<>();
        for (OfficialEntity officialEntity : officialRepository.findAll()) {
            officials.add(toOfficial(officialEntity));
        }

        return ResponseEntity.ok(officials);
    }

    @Override
    public ResponseEntity<Official> getOfficialById(Integer officialId) {
        Optional<OfficialEntity> officialEntity = officialRepository.findById(officialId);

        if(officialEntity.isPresent()) {
            return ResponseEntity.ok(toOfficial(officialEntity.get()));
        }

        // TODO : not working
        return ResponseEntity.notFound().build();
    }

    private OfficialEntity toOfficialEntity(OfficialDTO dto) {
        OfficialEntity entity = new OfficialEntity();

        try {
            entity.setTeam(teamRepository.findById(dto.getIdTeam()).get());
        } catch(NoSuchElementException e) {
            return null;
        }
        entity.setLevel(dto.getLevel());

        return entity;
    }

    public static Official toOfficial(OfficialEntity entity) {
        Official official = new Official();
        official.setId(entity.getId());
        official.setLevel(entity.getLevel());
        official.setTeam(TeamsApiController.toTeam(entity.getTeam()));

        return official;
    }
}

package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.OfficialsApi;
import ch.heigvd.amt.api.model.Official;
import ch.heigvd.amt.api.model.OfficialDTO;
import ch.heigvd.amt.entities.OfficialEntity;
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

    ////////////////// CREATE //////////////////
    @Override
    public ResponseEntity<Void> createOfficial(@Valid OfficialDTO official) {
        OfficialEntity entity = toOfficialEntity(official);
        if (entity == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        officialRepository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ////////////////// READ //////////////////
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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    ////////////////// UPDATE //////////////////
    @Override
    public ResponseEntity<Void> updateOfficialById(Integer officialId, @Valid OfficialDTO official) {
        OfficialEntity entity;

        try {
            entity = officialRepository.findById(officialId).get();
        } catch(NoSuchElementException e){
            System.out.println(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        entity = changeElements(entity, official);
        if(entity == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();


        officialRepository.save(entity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    ////////////////// DELETE //////////////////
    @Override
    public ResponseEntity<Void> deleteOfficialById(Integer officialId) {
        if (!officialRepository.existsById(officialId))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        officialRepository.deleteById(officialId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private OfficialEntity toOfficialEntity(OfficialDTO dto) {
        OfficialEntity entity = new OfficialEntity();

        try {
            entity.setTeam(teamRepository.findById(dto.getIdTeam()).get());
        } catch(NoSuchElementException e) {
            System.out.println(e.getMessage());

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

    private OfficialEntity changeElements(OfficialEntity entity, OfficialDTO dto) {
        Integer idTeam = dto.getIdTeam();
        if(idTeam != null) {
            try {
                entity.setTeam(teamRepository.findById(idTeam).get());
            } catch(NoSuchElementException e) {
                System.out.println(e.getMessage());

                return null;
            }
        }

        Integer level = dto.getLevel();
        if(level != null && level >= 1 && level <= 3)
            entity.setLevel(level);


        return entity;
    }
}

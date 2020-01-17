package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.OfficialsApi;
import ch.heigvd.amt.api.model.Official;
import ch.heigvd.amt.entities.OfficialEntity;
import ch.heigvd.amt.repositories.OfficialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OfficialsApiController implements OfficialsApi {

    @Autowired
    OfficialRepository officialRepository;

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

    public static Official toOfficial(OfficialEntity entity) {
        Official official = new Official();
        official.setId(entity.getId());
        official.setLevel(entity.getLevel());
        official.setTeam(TeamsApiController.toTeam(entity.getTeam()));

        return official;
    }
}

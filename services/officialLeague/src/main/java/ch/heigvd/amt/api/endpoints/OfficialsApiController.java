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

    public static Official toOfficial(OfficialEntity entity) {
        Official official = new Official();
        official.setId(entity.getId());
        official.setLevel(entity.getLevel());
        official.setTeam(TeamsApiController.toTeam(entity.getTeam()));

        return official;
    }
}

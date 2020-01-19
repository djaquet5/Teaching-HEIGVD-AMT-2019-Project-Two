package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.ConnectionApi;
import ch.heigvd.amt.api.model.CredentialDTO;
import ch.heigvd.amt.api.service.Authentication;
import ch.heigvd.amt.api.service.JWTToken;
import ch.heigvd.amt.entities.UserEntity;
import ch.heigvd.amt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.NoSuchElementException;

public class ConnectionApiController implements ConnectionApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Authentication authentication;

    @Autowired
    JWTToken jwtToken;

    @Override
    public ResponseEntity<String> login(@Valid CredentialDTO creds) {
        try {
            UserEntity userEntity = userRepository.findByEmail(creds.getEmail());

            if(!authentication.checkPassword(creds.getPassword(), userEntity.getPassword()))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            return ResponseEntity.ok(jwtToken.createToken(userEntity));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

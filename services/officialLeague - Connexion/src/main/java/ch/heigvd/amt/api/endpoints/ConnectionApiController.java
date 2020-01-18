package ch.heigvd.amt.api.endpoints;

import ch.heigvd.amt.api.ConnectionApi;
import ch.heigvd.amt.api.model.CredentialDTO;
import ch.heigvd.amt.api.model.Token;
import ch.heigvd.amt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public class ConnectionApiController implements ConnectionApi {

    @Autowired
    UserRepository userRepository;

    // TODO : on est dans la merde
    @Override
    public ResponseEntity<Token> login(@Valid CredentialDTO creds) {
//        userRepository.findByEmail(creds.getEmail())
        return null;
    }
}

package ch.heigvd.amt.api.service;

import ch.heigvd.amt.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTToken implements IJWTToken {

    @Value("${token.secret}")
    private String secret;

    @Override
    public String createToken(UserEntity userEntity) {
        Algorithm algo = Algorithm.HMAC256(secret);

        String token = JWT.create().withClaim("email", userEntity.getEmail())
                                    .withClaim("role", userEntity.getRole())
                                    .sign(algo);

        return "Bearer " + token;
    }

    @Override
    public DecodedToken verifyToken(String token) {
        String[] splittedToken = token.split(" ");

        if(splittedToken[0].equals("Bearer") && splittedToken.length == 2){
            Algorithm algo = Algorithm.HMAC256(secret);

            JWTVerifier verifier = JWT.require(algo).build();
            DecodedJWT decodedJWT = verifier.verify(splittedToken[1]);

            return DecodedToken.builder().email(decodedJWT.getClaim("email").asString())
                                         .role(decodedJWT.getClaim("role").asString())
                                         .build();
        }

        throw new JWTVerificationException("Not a valid token");
    }
}

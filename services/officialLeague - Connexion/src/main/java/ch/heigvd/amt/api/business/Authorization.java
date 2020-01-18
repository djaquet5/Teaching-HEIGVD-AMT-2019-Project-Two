package ch.heigvd.amt.api.business;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class Authorization /*implements IAuthorization*/ {

//    @Value("${token.secret}")
//    String secret;
//
//    @Override
//    public Token generateToken(User user) throws JWTCreationException {
//        Token token = new Token();
//        JWTCreator.Builder t = JWT.create().withIssuer("auth0")
//                .withClaim("email", user.getEmail())
//                .withClaim("ID", user.getID());
//
//        token.setToken(t.sign(Algorithm.HMAC256(secret)));
//
//        return token;
//    }
//
//    @Override
//    public businnessModel decodeToken(String token) {
//        try {
//            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
//                    .withIssuer("auth0").build();
//            DecodedJWT jwt = verifier.verify(token);
//
//            return new businnessModel(jwt.getClaim("ID").asInt(),jwt.getClaim("email").asString());
//        } catch (JWTVerificationException e) {
//            return null;
//        }
//    }
}

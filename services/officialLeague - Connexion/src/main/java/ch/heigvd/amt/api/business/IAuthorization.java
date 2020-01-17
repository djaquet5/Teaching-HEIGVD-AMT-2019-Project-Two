package ch.heigvd.amt.api.business;

import com.auth0.jwt.exceptions.JWTCreationException;


public interface IAuthorization {
    Token generateToken(User user) throws JWTCreationException;
    businnessModel decodeToken(String token);
}
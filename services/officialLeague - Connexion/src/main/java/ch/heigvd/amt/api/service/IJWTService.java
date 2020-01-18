package ch.heigvd.amt.api.service;

import ch.heigvd.amt.entities.UserEntity;

public interface IJWTService {
    String createToken(UserEntity userEntity);
    DecodedToken verifyToken(String token);
}

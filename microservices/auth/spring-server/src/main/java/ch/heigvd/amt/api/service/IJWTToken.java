package ch.heigvd.amt.api.service;

import ch.heigvd.amt.entities.UserEntity;

public interface IJWTToken {
    String createToken(UserEntity userEntity);
    DecodedToken verifyToken(String token);
}

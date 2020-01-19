package ch.heigvd.amt.api.service;

public interface IJWTToken {
    DecodedToken verifyToken(String token);
}

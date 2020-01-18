package ch.heigvd.amt.api.service;

public interface IAuthentication {
    String hashPassword(String password);
    boolean checkPassword(String password, String hashedPassword);
}

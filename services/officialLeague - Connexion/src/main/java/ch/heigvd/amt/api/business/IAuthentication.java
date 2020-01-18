package ch.heigvd.amt.api.business;

public interface IAuthentication {
    String hashPassword(String plainTextPassword);
    boolean checkPassword(String plainTextPassword, String hashedPassword);
}
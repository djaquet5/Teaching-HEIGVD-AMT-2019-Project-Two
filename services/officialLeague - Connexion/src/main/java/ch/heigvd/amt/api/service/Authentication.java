package ch.heigvd.amt.api.service;

import org.mindrot.jbcrypt.BCrypt;

public class Authentication implements IAuthentication {
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}

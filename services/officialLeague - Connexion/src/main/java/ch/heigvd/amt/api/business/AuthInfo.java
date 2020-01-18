package ch.heigvd.amt.api.business;

import lombok.Getter;

@Getter
public class AuthInfo {

    private String email;
    private int id;

    public AuthInfo(int id, String email) {
        this.id = id;
        this.email = email;
    }

}
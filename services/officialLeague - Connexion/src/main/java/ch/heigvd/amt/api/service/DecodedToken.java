package ch.heigvd.amt.api.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DecodedToken {
    String email;
    String role;

    public boolean isAdmin() {
        return role != null && role.equals("admin");
    }
}

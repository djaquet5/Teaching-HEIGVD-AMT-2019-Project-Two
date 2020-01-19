package ch.heigvd.amt.api.service;

import ch.heigvd.amt.api.util.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DecodedToken {
    String email;
    String role;

    public boolean isAdmin() {
        return role != null && role.equals(Role.ADMIN);
    }
}

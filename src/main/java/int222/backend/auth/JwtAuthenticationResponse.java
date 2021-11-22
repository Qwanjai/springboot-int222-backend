package int222.backend.auth;

import int222.backend.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class JwtAuthenticationResponse {
    private User user;
    private final String jwt;

}

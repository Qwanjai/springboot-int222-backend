package int222.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class JwtAuthenticationResponse {
    private final String jwt;

}

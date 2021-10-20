package int222.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class JwtAuthenticationRequest {
    private String username;
    private String password;



}

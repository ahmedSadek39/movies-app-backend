package movie.security.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import movie.security.enums.Role;

@Builder
@Getter
@Setter
public class LoginResponse {

    private String token;
    private Role role;

}

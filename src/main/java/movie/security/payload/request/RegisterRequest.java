package movie.security.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import movie.security.enums.Role;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    @Size(min = 6, max = 50)
    private String username;
    private String email;
    @NotNull
    private Role role;
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}

package movie.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import movie.security.payload.request.LoginRequest;
import movie.security.payload.request.RegisterRequest;
import movie.security.payload.response.LoginResponse;
import movie.security.payload.response.RegisterResponse;
import movie.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static movie.utility.http.handler.RequestHandler.handleRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest httpRequest) {
        return handleRequest(() -> userService.login(loginRequest, httpRequest));
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest, HttpServletRequest httpRequest) {
        return handleRequest(() -> userService.register(registerRequest, httpRequest));
    }

}

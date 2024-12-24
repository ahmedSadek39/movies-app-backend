package movie.security.service;

import jakarta.servlet.http.HttpServletRequest;
import movie.exception.handler.types.InvalidCredentialsException;
import movie.exception.handler.types.UserAlreadyExistsException;
import movie.exception.handler.types.UserNotFoundException;
import movie.security.auth.JwtGenerator;
import movie.security.model.Users;
import movie.security.payload.request.LoginRequest;
import movie.security.payload.request.RegisterRequest;
import movie.security.payload.response.LoginResponse;
import movie.security.payload.response.RegisterResponse;
import movie.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, HttpServletRequest httpRequest){

        if (!userRepo.existsByUsername(loginRequest.getUsername())) {
            throw new UserNotFoundException("Username does not exist");
        }

        Users user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = getToken(loginRequest.getUsername(), user.getRole().name());

        return ResponseEntity.ok().body(
                LoginResponse.builder().role(user.getRole()).token(token).build()
        );
    }

    public ResponseEntity<RegisterResponse> register(RegisterRequest registerRequest, HttpServletRequest httpRequest){

        if (userRepo.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username already exist");
        }

        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        user.setCreatedAt(new Date());

        Users savedUser = userRepo.save(user);

        String token = getToken(savedUser.getUsername(), savedUser.getRole().name());

        return ResponseEntity.ok().body(
                RegisterResponse.builder().role(savedUser.getRole()).token(token).build()
        );
    }

    private String getToken(String username, String role){
        return jwtGenerator.generateToken(username, role);
    }

}

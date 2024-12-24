package movie.security.repository;

import movie.security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, String> {

    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

}

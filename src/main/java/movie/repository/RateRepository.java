package movie.repository;

import movie.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rating, String> {
    List<Rating> findBySourceAndImdbID(String username, String imdbID);
}

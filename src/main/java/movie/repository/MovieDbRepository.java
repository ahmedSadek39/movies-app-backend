package movie.repository;

import movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDbRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    boolean existsByImdbID(String id);
    Optional<Movie> findByImdbID(String imdbID);
    @Modifying
    @Transactional
    int deleteAllByImdbIDIn(List<String> imdbIDs);


}

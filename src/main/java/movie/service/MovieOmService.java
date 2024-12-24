package movie.service;

import movie.dto.MovieDetailsDto;
import movie.payload.request.MovieRequestDto;
import movie.payload.response.MovieResponseDto;
import movie.repository.MovieOmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieOmService {

    private final MovieOmRepository movieRepository;

    @Autowired
    public MovieOmService(MovieOmRepository movieOmRepository) {
        this.movieRepository = movieOmRepository;
    }

   public MovieResponseDto getMovies(MovieRequestDto req){
        return movieRepository.getMovies(req);
   }

    public MovieDetailsDto getMovieDetailsById(String imdbID){
        return movieRepository.getMovieDetailsById(imdbID);
    }


}
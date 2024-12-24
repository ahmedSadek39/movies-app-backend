package movie.controller;

import movie.dto.MovieDetailsDto;
import movie.payload.request.MovieRequestDto;
import movie.payload.response.MovieResponseDto;
import movie.payload.validation.MovieValidation;
import movie.security.auth.SecurityConstants;
import movie.service.MovieOmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static movie.utility.http.handler.RequestHandler.handleRequest;

@RestController
@RequestMapping("/api/movie/om")
@CrossOrigin("*")
@PreAuthorize(SecurityConstants.AUTHORIZE_ADMIN)
public class MovieOmController {

    private final MovieOmService movieOmService;

    @Autowired
    public MovieOmController(MovieOmService movieOmService) {
        this.movieOmService = movieOmService;
    }

    @PostMapping("/search")
    public MovieResponseDto getMoviesFromOm(@RequestBody MovieRequestDto movieRequestDto) {
        return handleRequest(() -> {
            MovieValidation.validateGetMoviesRequest(movieRequestDto);
            return movieOmService.getMovies(movieRequestDto);
        });
    }

    @GetMapping("{id}")
    public MovieDetailsDto getMovieDetails(@PathVariable String imdbId) {
        return handleRequest(() -> {
            MovieValidation.validateMovieIdRequest(imdbId);
            return movieOmService.getMovieDetailsById(imdbId);
        });
    }









}

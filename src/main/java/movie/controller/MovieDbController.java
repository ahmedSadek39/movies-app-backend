package movie.controller;

import movie.dto.MovieDetailsDto;
import movie.dto.MovieSummaryDto;
import movie.dto.RateMovieDto;
import movie.model.Rating;
import movie.payload.request.DeleteMoviesRequest;
import movie.payload.request.MovieRequestDto;
import movie.payload.request.SaveMoviesRequest;
import movie.payload.response.MovieDetailsResponse;
import movie.payload.validation.MovieValidation;
import movie.security.auth.SecurityConstants;
import movie.service.MovieDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static movie.utility.http.handler.RequestHandler.handleRequest;

@RestController
@RequestMapping("/api/movie/db")
@CrossOrigin("*")
@PreAuthorize(SecurityConstants.AUTHORIZE_ADMIN_OR_USER)
public class MovieDbController {

    private final MovieDbService movieDbService;

    @Autowired
    public MovieDbController(MovieDbService movieDbService) {
        this.movieDbService = movieDbService;
    }

    @PostMapping("/search")
    public Page<MovieSummaryDto> getMoviesFromDb(@RequestBody MovieRequestDto movieRequestDto) {
        return handleRequest(() -> movieDbService.getMovies(movieRequestDto) );
    }

    @GetMapping("{id}")
    public MovieDetailsResponse getMovieByIdFromDb(@PathVariable String id) {
        return handleRequest(() -> {
            MovieValidation.validateGetMovieDetailsRequest(id);
            return movieDbService.getMovieDetailsById(id);
        });
    }

    @PostMapping("/save")
    public List<MovieDetailsDto> saveMoviesInDb(@RequestBody SaveMoviesRequest saveMoviesRequest) {
        return handleRequest(() -> {
            MovieValidation.validateSaveMoviesRequest(saveMoviesRequest.getIds());
            return movieDbService.saveMovies(saveMoviesRequest.getIds());
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMoviesInDb(@RequestBody DeleteMoviesRequest deleteMoviesRequest) {
        return handleRequest(() -> {
            MovieValidation.validateDeleteMoviesRequest(deleteMoviesRequest.getIds());
            return movieDbService.deleteMovies(deleteMoviesRequest.getIds());
        });
    }

    @PostMapping("/rate")
    public ResponseEntity<Rating> rateMovie(@RequestBody RateMovieDto rateDto) {
        return handleRequest(() -> {
            MovieValidation.validateRateFilmRequest(rateDto);
            return movieDbService.rateMovie(rateDto);
        });
    }







}

package movie.service;

import movie.dto.MovieDetailsDto;
import movie.dto.MovieSummaryDto;
import movie.dto.RateMovieDto;
import movie.exception.handler.types.BadRequestException;
import movie.exception.handler.types.MovieNotFoundException;
import movie.model.Movie;
import movie.model.Rating;
import movie.payload.request.MovieRequestDto;
import movie.payload.response.MovieDetailsResponse;
import movie.repository.MovieDbRepository;
import movie.repository.MovieOmRepository;
import movie.repository.RateRepository;
import movie.specification.MovieSpecification;
import movie.utility.RatingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieDbService {

    private final MovieDbRepository movieDbRepository;
    private final MovieOmRepository movieOmRepository;
    private final RateRepository rateRepository;
    private final MovieMapperService movieMapperService;

    @Autowired
    public MovieDbService(MovieOmRepository movieOmRepository, MovieDbRepository movieDbRepository, RateRepository rateRepository, MovieMapperService movieMapperService) {
        this.movieDbRepository = movieDbRepository;
        this.movieOmRepository = movieOmRepository;
        this.rateRepository = rateRepository;
        this.movieMapperService = movieMapperService;
    }

    public MovieDetailsResponse getMovieDetailsById(String imdbID) {
        Optional<Movie> movie = movieDbRepository.findByImdbID(imdbID);

        if(movie.isPresent()){
            MovieDetailsResponse movieDetailsResponse = movieMapperService.convertToResponse(movie.get());
            movieDetailsResponse.setAvgRating(RatingUtility.calculateAverageRating(movie.get().getRatings()));
            return movieDetailsResponse;
        }
        throw new MovieNotFoundException("Movie Not Found");
    }

    public Page<MovieSummaryDto> getMovies(MovieRequestDto req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getPageSize());
        Specification<Movie> spec = MovieSpecification.withFilters(req.getKeyword(), req.getYear(), req.getType());

        Page<Movie> moviePage = movieDbRepository.findAll(spec, pageable);

        List<MovieSummaryDto> movieDtos = moviePage.stream()
                .map(movie -> new MovieSummaryDto(
                        movie.getTitle(),
                        movie.getYear(),
                        movie.getImdbID(),
                        movie.getType(),
                        movie.getPoster(),
                        RatingUtility.calculateAverageRating(movie.getRatings())
                ))
                .toList();

        return new PageImpl<>(movieDtos, pageable, moviePage.getTotalElements());
    }

    @Transactional
    public List<MovieDetailsDto> saveMovies(List<String> ids) {
        List<Movie> films = new ArrayList<>();
        int currentIndex = 0;
        for (String id : ids){
            MovieDetailsDto movieDetailsById = movieOmRepository.getMovieDetailsById(id);
            for(int i = 0; i < movieDetailsById.getRatings().size(); i++){
                movieDetailsById.getRatings().get(i).setImdbID(ids.get(currentIndex));
            }
            if(!movieDbRepository.existsByImdbID(ids.get(currentIndex))){
                films.add(movieMapperService.convertToEntity(movieDetailsById));
            }
            currentIndex++;
        }
        return movieDbRepository.saveAll(films).stream().map(movieMapperService::convertToDto).toList();
    }


    @Transactional
    public ResponseEntity<Boolean> deleteMovies(List<String> imdbIDs) {
        int batchSize = 5;
        int moviesDeletedCount = 0;
        for (int i = 0; i < imdbIDs.size(); i += batchSize) {
            List<String> batch = imdbIDs.subList(i, Math.min(i + batchSize, imdbIDs.size()));
            moviesDeletedCount+= movieDbRepository.deleteAllByImdbIDIn(batch);
        }
        return ResponseEntity.ok().body(moviesDeletedCount == imdbIDs.size());
    }


    @Transactional
    public ResponseEntity<Rating> rateMovie(RateMovieDto rateMovieDto) {
        Optional<Movie> movie = movieDbRepository.findByImdbID(rateMovieDto.getImdbID());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Rating> bySourceAndImdbID = rateRepository.findBySourceAndImdbID(username, rateMovieDto.getImdbID());

        if(!bySourceAndImdbID.isEmpty()){
            throw new BadRequestException("You Rated This film before");
        }

        if(movie.isPresent()){
            Rating rating = new Rating();
            rating.setImdbID(movie.get().getImdbID());
            rating.setSource(username);
            rating.setValue(String.valueOf(rateMovieDto.getRate()));
            return ResponseEntity.ok().body(rateRepository.save(rating));
        }
        throw new MovieNotFoundException("Movie isn't found");
    }


}
package movie.service;

import movie.dto.MovieDetailsDto;
import movie.dto.MovieSummaryDto;
import movie.model.Movie;
import movie.payload.response.MovieDetailsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieMapperService {

    private final ModelMapper modelMapper;

    @Autowired
    public MovieMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieDetailsDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDetailsDto.class);
    }
    public MovieDetailsResponse convertToResponse(Movie movie) {
        return modelMapper.map(movie, MovieDetailsResponse.class);
    }

    public Movie convertToEntity(MovieDetailsDto movieDetailsDto) {
        return modelMapper.map(movieDetailsDto, Movie.class);
    }

    public MovieSummaryDto convertToMovieDb(Movie movie) {
        return modelMapper.map(movie, MovieSummaryDto.class);
    }
}
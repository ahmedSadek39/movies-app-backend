package movie.payload.validation;

import movie.dto.RateMovieDto;
import movie.exception.handler.types.BadRequestException;
import movie.payload.request.MovieRequestDto;
import movie.utility.ValidationUtility;

import java.util.List;

public class MovieValidation {

    public static void validateGetMoviesRequest(MovieRequestDto req){
        if (ValidationUtility.isValidString(req.getKeyword())){
            throw new BadRequestException("Movie id is missing or empty");
        }
    }

    public static void validateMovieIdRequest(String movieId){
        if (ValidationUtility.isValidString(movieId)){
            throw new BadRequestException("Movie id is missing or empty");
        }
    }

    public static void validateGetMovieDetailsRequest(String id){
        if (ValidationUtility.isValidString(id)){
            throw new BadRequestException("Movie id is missing or empty");
        }
    }

    public static void validateSaveMoviesRequest(List<String> ids){
        if (ValidationUtility.isNonEmptyArray(ids)){
            throw new BadRequestException("Movies ids are missing or empty");
        }
    }

    public static void validateDeleteMoviesRequest(List<String> ids){
        if (ValidationUtility.isNonEmptyArray(ids)){
            throw new BadRequestException("Movies ids are missing or empty");
        }
    }

    public static void validateRateFilmRequest(RateMovieDto rate){
        if (ValidationUtility.isValidString(rate.getImdbID())){
            throw new BadRequestException("Movie id is missing or empty");
        }
        if (rate.getRate() == null || Double.parseDouble(rate.getRate()) < 0){
            throw new BadRequestException("Rate is missing or less than zero");
        }
    }
}

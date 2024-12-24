package movie.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import movie.dto.MovieDto;

import java.util.ArrayList;

@Getter
@Setter
public class MovieResponseDto {

    @JsonProperty("Search")
    private ArrayList<MovieDto> movies;
    private String totalResults;
    @JsonProperty("Response")
    private String response;

    @Override
    public String toString() {
        return "MovieResponseDto{" +
                "movies=" + movies +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}

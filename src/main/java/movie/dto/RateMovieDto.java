package movie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateMovieDto {

    private String rate;
    private String imdbID;

    @Override
    public String toString() {
        return "RateMovieDto{" +
                "rate='" + rate + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }
}

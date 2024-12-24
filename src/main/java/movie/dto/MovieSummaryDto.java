package movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MovieSummaryDto {

    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;
    private Double avgRating;

    @Override
    public String toString() {
        return "MovieSummaryDto{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                ", avgRating=" + avgRating +
                '}';
    }
}

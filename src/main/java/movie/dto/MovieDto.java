package movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Poster")
    private String poster;

    @Override
    public String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}

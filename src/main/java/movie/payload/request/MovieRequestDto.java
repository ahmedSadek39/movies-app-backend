package movie.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {

    @JsonProperty(required = false)
    private String keyword;
    @JsonProperty(required = false)
    private String type;
    @JsonProperty(required = false)
    private String year;
    @JsonProperty(required = false, defaultValue = "1")
    private Integer page;
    @JsonProperty(required = false, defaultValue = "10")
    private Integer pageSize;

    @Override
    public String toString() {
        return "MovieRequestDto{" +
                "keyword='" + keyword + '\'' +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}

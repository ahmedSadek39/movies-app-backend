package movie.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import movie.dto.RatingDto;

import java.util.ArrayList;

@Getter
@Setter
public class MovieDetailsResponse {

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    @JsonIgnore
    private ArrayList<RatingDto> ratings;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String type;
    private String dVD;
    private String boxOffice;
    private String production;
    private String website;
    private String response;
    private Double avgRating;

    @Override
    public String toString() {
        return "MovieDetailsDto{" +
                "imdbID='" + imdbID + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", ratings=" + ratings +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", type='" + type + '\'' +
                ", dVD='" + dVD + '\'' +
                ", boxOffice='" + boxOffice + '\'' +
                ", production='" + production + '\'' +
                ", website='" + website + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}

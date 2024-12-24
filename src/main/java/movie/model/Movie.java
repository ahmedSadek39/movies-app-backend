package movie.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "u374042800_movie_task", name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imdbID", unique = true, nullable = false)
    private String imdbID;

    @Column(name = "Title")
    private String title;

    @Column(name = "Year")
    private String year;

    @Column(name = "Rated")
    private String rated;

    @Column(name = "Released")
    private String released;

    @Column(name = "Runtime")
    private String runtime;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "Director")
    private String director;

    @Column(name = "Writer")
    private String writer;

    @Column(name = "Actors")
    private String actors;

    @Column(name = "Plot")
    private String plot;

    @Column(name = "Language")
    private String language;

    @Column(name = "Country")
    private String country;

    @Column(name = "Awards")
    private String awards;

    @Column(name = "Poster")
    private String poster;

    @Column(name = "Metascore")
    private String metascore;

    @Column(name = "imdbRating")
    private String imdbRating;

    @Column(name = "imdbVotes")
    private String imdbVotes;

    @Column(name = "Type")
    private String type;

    @Column(name = "DVD")
    private String dVD;

    @Column(name = "BoxOffice")
    private String boxOffice;

    @Column(name = "Production")
    private String production;

    @Column(name = "Website")
    private String website;

    @Column(name = "Response")
    private String response;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Rating> ratings;

}

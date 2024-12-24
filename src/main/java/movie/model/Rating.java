package movie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "u374042800_movie_task", name = "rating")
public class Rating {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imdbID")
    private String imdbID;

    @Column(name = "Source")
    private String source;

    @Column(name = "Value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "imdbID", referencedColumnName = "imdbID", insertable = false, updatable = false)
    @JsonBackReference
    private Movie movie;
}

package ee.taltech.iti0302.webproject.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ee.taltech.iti0302.webproject.service.GenreService;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@Entity
public class Movie {
    @Id
    private int id;
    private String title;
    private String overview;
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    @JsonProperty("vote_average")
    private double voteAverage;
    @JsonProperty("genre_ids")
    @Transient
    private Set<Integer> genreIds;

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;

    @ManyToMany
    @JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors;
}

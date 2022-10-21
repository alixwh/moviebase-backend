package ee.taltech.iti0302.webproject.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter @Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private int year;
    private double rating;

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Genre> actors;

    @ManyToMany
    @JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Genre> directors;

}

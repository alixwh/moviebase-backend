package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.entities.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface MovieRepository extends JpaRepositoryImplementation<Movie, Integer> {
    @Query(value="SELECT * FROM movie where date_part('year', release_date) = :year", nativeQuery = true)
    List<Movie> findAllByReleaseDateYear(int year);

    @Query(value="SELECT * FROM movie where date_part('year', release_date) in :years", nativeQuery = true)
    List<Movie> findAllByReleaseDateYearIn(List<Integer> years);

    List<Movie> findByTitleContainingIgnoreCase(String movieName);

    @Query(value = "SELECT * from movie where id in (SELECT movie_id FROM movie_genre where genre_id in ?1) AND date_part('year', release_date)  in ?2", nativeQuery = true)
    List<Movie> findAllByGenresInAndReleaseDateYearIn(List<Integer> genres, List<Integer> years);

    List<Movie> findAllByGenresIn(List<Genre> genres);
}

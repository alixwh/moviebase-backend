package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface MovieRepository extends JpaRepositoryImplementation<Movie, Integer> {
    @Query(value="SELECT * FROM movie where date_part('year', release_date) = :year", nativeQuery = true)
    List<Movie> findAllByReleaseDate(int year);

    @Query(value="SELECT * FROM movie where date_part('year', release_date) in :years", nativeQuery = true)
    Page<Movie> findAllByReleaseDateIn(List<Integer> years, Pageable pageable);

    List<Movie> findByTitleContainingIgnoreCase(String movieName);

    @Query(value = "SELECT * from movie where id in (SELECT movie_id FROM movie_genre where genre_id in :genres) AND date_part('year', release_date) in :years",
            countQuery = "SELECT count(*) FROM movie",
            nativeQuery = true)
    Page<Movie> findAllByGenresInAndReleaseDateIn(List<Integer> genres, List<Integer> years, Pageable pageable);

    @Query(value = "SELECT * from movie where id in (SELECT movie_id FROM movie_genre where genre_id in ?1)", nativeQuery = true)
    Page<Movie> findAllByGenresIn(List<Integer> genres, Pageable pageable);
}

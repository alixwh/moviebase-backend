package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Movie;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.time.LocalDate;
import java.util.List;


public interface MovieRepository extends JpaRepositoryImplementation<Movie, Integer> {
    List<Movie> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
}

package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Movie;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;


public interface MovieRepository extends JpaRepositoryImplementation<Movie, Integer> {
}

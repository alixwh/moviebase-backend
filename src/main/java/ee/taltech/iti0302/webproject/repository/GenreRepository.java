package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Genre;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;


public interface GenreRepository extends JpaRepositoryImplementation<Genre, Integer> {
    List<Genre> findByIdIn(List<Integer> genres);
    List<Genre> findAllByIdIn(List<Integer> genres);
}

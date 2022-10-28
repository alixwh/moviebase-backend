package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.classes.Genre;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface GenreRepository extends JpaRepositoryImplementation<Genre, Integer> {
}

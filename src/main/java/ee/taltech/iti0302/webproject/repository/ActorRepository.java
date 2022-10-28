package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Actor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ActorRepository extends JpaRepositoryImplementation<Actor, Integer> {
}

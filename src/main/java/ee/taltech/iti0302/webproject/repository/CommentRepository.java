package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Comment;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface CommentRepository extends JpaRepositoryImplementation<Comment, Integer> {
}

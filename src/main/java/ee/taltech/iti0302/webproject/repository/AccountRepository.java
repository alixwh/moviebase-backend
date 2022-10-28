package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Account;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface AccountRepository extends JpaRepositoryImplementation<Account, Integer> {
}
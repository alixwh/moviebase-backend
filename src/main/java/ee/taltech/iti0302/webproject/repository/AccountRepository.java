package ee.taltech.iti0302.webproject.repository;

import ee.taltech.iti0302.webproject.entities.Account;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface AccountRepository extends JpaRepositoryImplementation<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
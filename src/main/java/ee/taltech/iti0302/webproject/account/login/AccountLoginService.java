package ee.taltech.iti0302.webproject.account.login;

import ee.taltech.iti0302.webproject.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountLoginService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with name %s not found";
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository
                .findByUsername(username).orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

}

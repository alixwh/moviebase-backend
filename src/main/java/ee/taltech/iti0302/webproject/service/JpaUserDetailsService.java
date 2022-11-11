package ee.taltech.iti0302.webproject.service;


import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.mapper.AccountExternalMapper;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final AccountRepository userRepository;
    private final AccountExternalMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> byUsername = userRepository
                .findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        return mapper.accountToAccountExternalDto(byUsername.get());
    }
}
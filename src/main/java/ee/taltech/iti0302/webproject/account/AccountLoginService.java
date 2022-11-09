package ee.taltech.iti0302.webproject.account;

import ee.taltech.iti0302.webproject.account.registration.AccountLoginMapper;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountLoginService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG = "user with name %s not found";
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountLoginMapper accountLoginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }
    public String signUpAccount(AccountLoginDto account) {
        boolean accountExists = accountRepository
                .findByUsername(account.getUsername())
                .isPresent();
        if (accountExists) {
            throw new IllegalStateException("name already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);
        Account toAccount = accountLoginMapper.accountLoginTOAccount(account);
        accountRepository.save(toAccount);
        return "it works";
    }

}

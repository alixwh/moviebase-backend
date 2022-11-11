package ee.taltech.iti0302.webproject.account.registration;

import ee.taltech.iti0302.webproject.account.AccountRole;
import ee.taltech.iti0302.webproject.account.login.AccountLoginDto;
import ee.taltech.iti0302.webproject.account.login.AccountLoginMapper;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountLoginMapper accountLoginMapper;

    public String register(RegistrationRequest request) {
        return signUpAccount(
                new AccountLoginDto(request.getUsername(),
                        request.getPassword(),
                        AccountRole.ROLE_USER)
        );
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

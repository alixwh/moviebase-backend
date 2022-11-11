package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.mapper.AccountExternalMapper;
import ee.taltech.iti0302.webproject.model.AccountExternalDto;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountExternalService {
    private final AccountRepository accountRepository;
    private final AccountExternalMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public boolean isInDatabase(String username) {
        return accountRepository.findByUsername(username).isPresent();
    }

    public Account save(AccountExternalDto account) {
        if (isInDatabase(account.getUsername())) {
            return null;
        }
        Account account1 = mapper.accountExternalDtoToAccount(account);
        account1.setPassword(passwordEncoder.encode(account1.getPassword()));
        return accountRepository.save(account1);
    }
}

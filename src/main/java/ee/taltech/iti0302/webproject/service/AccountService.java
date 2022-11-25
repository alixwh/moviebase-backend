package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.account.AccountRole;
import ee.taltech.iti0302.webproject.account.login.LoginResponse;
import ee.taltech.iti0302.webproject.account.register.CreateAccountRequest;
import ee.taltech.iti0302.webproject.account.register.RegisterResponse;
import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.mapper.AccountMapper;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import ee.taltech.iti0302.webproject.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<AccountDto> findAll() {
        return accountMapper.toDtoList(accountRepository.findAll());
    }

    public AccountDto findById(int id) {
        return accountMapper.toDto(accountRepository.findById(id).orElse(null));
    }

    public RegisterResponse createAccount(CreateAccountRequest createAccountRequest) {
        Account account = accountMapper.toEntity(createAccountRequest);
        account.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        account.setAccountRole(AccountRole.ROLE_USER);

        // TODO throw exception if name exist already

        accountRepository.save(account);
        // very temporary thing: !!!
        return new RegisterResponse("register successful");
    }

    public LoginResponse login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isEmpty()) {
            throw new ApplicationContextException("No user found");
        }
        Account account = optionalAccount.get();
        if (passwordEncoder.matches(password, account.getPassword())) {
            String token = tokenProvider.generateToken(username, account.getId(), account.getAccountRole());
            return new LoginResponse(token);
        } else {
            throw new ApplicationContextException("Wrong username or password");
        }
    }
}

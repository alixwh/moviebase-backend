package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.account.register.RegisterResponse;
import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Account;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.mapper.AccountMapper;
import ee.taltech.iti0302.webproject.mapper.AccountMapperImpl;
import ee.taltech.iti0302.webproject.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Spy
    private AccountMapper accountMapper = new AccountMapperImpl();
    @Spy
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @InjectMocks
    private AccountService accountService;

    public static final Account account = Account.builder().id(1).username("test").password("test").build();
    public static final AccountDto accountDto = AccountDto.builder().id(1).username("test").password("test").build();

    @Test
    void findAll() {
        given(accountRepository.findAll()).willReturn(List.of(account));
        var result = accountService.findAll();
        then(accountMapper).should().toDto(account);
        then(accountRepository).should().findAll();
        assertEquals(List.of(accountDto), result);
    }

    @Test
    void findById() {
        given(accountRepository.findById(1)).willReturn(Optional.of(account));

        var result= accountService.findById(1);

        then(accountMapper).should().toDto(account);
        then(accountRepository).should().findById(1);
        assertEquals(accountDto, result);
    }

    @Test
    void findByName() {
        given(accountRepository.findByUsername("test")).willReturn(Optional.of(account));

        var result= accountService.findByName("test");

        then(accountMapper).should().toDto(account);
        then(accountRepository).should().findByUsername("test");
        assertEquals(accountDto, result);
    }

    @Test
    void createAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        RegisterResponse account1 = accountService.createAccount("test", "test");
        assertEquals(account1, new RegisterResponse("register successful"));
    }
}
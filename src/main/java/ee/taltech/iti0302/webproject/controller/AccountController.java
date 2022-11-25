package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.account.login.LoginRequest;
import ee.taltech.iti0302.webproject.account.login.LoginResponse;
import ee.taltech.iti0302.webproject.account.register.CreateAccountRequest;
import ee.taltech.iti0302.webproject.account.register.RegisterResponse;
import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("account/{id}")
    public AccountDto getAccountById(@PathVariable("id") int id) {
        return accountService.findById(id);
    }

    @GetMapping("account")
    public List<AccountDto> getAccounts(Principal principal) {
        return accountService.findAll();
    }

    @PostMapping("public/register")
    public RegisterResponse createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @PostMapping("public/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return accountService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

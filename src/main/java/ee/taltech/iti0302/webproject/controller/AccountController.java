package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("account/{id}")
    public AccountDto getAccountById(@PathVariable("id") int id) {
        return accountService.findById(id);
    }

    @GetMapping("accounts")
    public List<AccountDto> getAccounts() {
        return accountService.findAll();
    }
}

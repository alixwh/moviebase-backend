package ee.taltech.iti0302.webproject.controller;

import ee.taltech.iti0302.webproject.dto.AccountDto;
import ee.taltech.iti0302.webproject.model.AccountExternalDto;
import ee.taltech.iti0302.webproject.service.AccountExternalService;
import ee.taltech.iti0302.webproject.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ee.taltech.iti0302.webproject.model.AccountRole.ROLE_USER;


@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountExternalService service;

    @GetMapping
    public List<AccountDto> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable int id) {
        return accountService.findById(id);
    }

    @PostMapping("/save")
    public void saveAccount(@RequestBody AppUser appUser) {
        service.save(new AccountExternalDto(appUser.getUsername(), appUser.getPassword(), ROLE_USER));
    }


}
@Data
class AppUser {
    String username;
    String password;
}
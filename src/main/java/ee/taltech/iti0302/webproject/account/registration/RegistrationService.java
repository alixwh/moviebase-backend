package ee.taltech.iti0302.webproject.account.registration;

import ee.taltech.iti0302.webproject.account.AccountLoginDto;
import ee.taltech.iti0302.webproject.account.AccountLoginService;
import ee.taltech.iti0302.webproject.account.AccountRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AccountLoginService accountLoginService;

    public String register(RegistrationRequest request) {
        return accountLoginService.signUpAccount(
                new AccountLoginDto(request.getUsername(),
                        request.getPassword(),
                        AccountRole.USER)
        );
    }
}

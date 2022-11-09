package ee.taltech.iti0302.webproject.account;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AccountLoginDto  {

    private int id;
    private String username;
    private String password;
    private AccountRole accountRole;
    private Boolean locked = false;

    public AccountLoginDto(String userName,
                           String password,
                           AccountRole accountRole) {
        this.username = userName;
        this.password = password;
        this.accountRole = accountRole;
    }

}

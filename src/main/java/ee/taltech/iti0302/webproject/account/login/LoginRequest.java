package ee.taltech.iti0302.webproject.account.login;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

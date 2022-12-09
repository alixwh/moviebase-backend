package ee.taltech.iti0302.webproject.account.register;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountRequest {
    private String username;
    private String password;

}

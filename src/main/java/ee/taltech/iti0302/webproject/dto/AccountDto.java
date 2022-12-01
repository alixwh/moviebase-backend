package ee.taltech.iti0302.webproject.dto;

import ee.taltech.iti0302.webproject.account.AccountRole;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class AccountDto {
    private int id;
    private String username;
    private String password;
    private Set<String> friendList;
    private Map<MovieDto, String> movieStateMap;
    private AccountRole accountRole;
    private Boolean locked = false;
}

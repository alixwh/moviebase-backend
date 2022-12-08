package ee.taltech.iti0302.webproject.dto;

import ee.taltech.iti0302.webproject.account.AccountRole;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@Builder
public class AccountDto {
    private int id;
    private String username;
    private String password;
    private Set<String> friendList;
    private Map<MovieDto, String> movieStateMap;
    private AccountRole accountRole;
    @Builder.Default
    private Boolean locked = false;
}

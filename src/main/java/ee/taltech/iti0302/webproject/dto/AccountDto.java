package ee.taltech.iti0302.webproject.dto;

import ee.taltech.iti0302.webproject.account.AccountRole;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@Builder
public class AccountDto {
    private int id;
    private String username;
    private String password;
    @Builder.Default
    private Set<String> friendList = new HashSet<>();
    @Builder.Default
    private Map<MovieDto, String> movieStateMap = new HashMap<>();
    @Builder.Default
    private AccountRole accountRole = AccountRole.ROLE_USER;
    @Builder.Default
    private Boolean locked = false;
}

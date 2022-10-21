package ee.taltech.iti0302.webproject.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AccountDto {
    private int id;
    private String username;
    private String password;
    private Set<String> friendList;
    private Set<String> moviesList;
}

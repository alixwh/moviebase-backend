package ee.taltech.iti0302.webproject.dto;

import ee.taltech.iti0302.webproject.repository.Account;
import ee.taltech.iti0302.webproject.repository.Movie;

import java.util.Set;

public class AccountDto {
    private int id;
    private String username;
    private String password;
    private Set<Account> friendList;
    private Set<Movie> movies;

}

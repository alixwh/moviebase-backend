package ee.taltech.iti0302.webproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "account_account_friendlist", joinColumns = @JoinColumn(name = "account1_id"), inverseJoinColumns = @JoinColumn(name = "account2_id"))
    private Set<Account> friends;

    @ManyToMany
    @JoinTable(name = "account_movie", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;
}
package ee.taltech.iti0302.webproject.entities;

import ee.taltech.iti0302.webproject.account.AccountRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter @Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AccountRole accountRole = AccountRole.ROLE_USER;
    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "account_account_friendlist", joinColumns = @JoinColumn(name = "account1_id"), inverseJoinColumns = @JoinColumn(name = "account2_id"))
    private Set<Account> friends = new HashSet<>();

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "account_movie", joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "movie_id")
    @Column(name = "state")
    private Map<Movie, String> movieMap = new HashMap<>();

    public void addMovie(Movie movie, String state) {
        movieMap.put(movie, state);
    }
}

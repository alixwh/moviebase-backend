package ee.taltech.iti0302.webproject.entities;

import ee.taltech.iti0302.webproject.account.AccountRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Getter @Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountRole accountRole;
    @Builder.Default
    private Boolean locked = false;

    @ManyToMany
    @JoinTable(name = "account_account_friendlist", joinColumns = @JoinColumn(name = "account1_id"), inverseJoinColumns = @JoinColumn(name = "account2_id"))
    private Set<Account> friends;

    @ElementCollection
    @CollectionTable(name = "account_movie", joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")})
    @MapKeyJoinColumn(name = "movie_id")
    @Column(name = "state")
    private Map<Movie, String> movieMap;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(accountRole.name());
        return Collections.singletonList(authority);
    }

    public void addMovie(Movie movie, String state) {
        movieMap.put(movie, state);
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

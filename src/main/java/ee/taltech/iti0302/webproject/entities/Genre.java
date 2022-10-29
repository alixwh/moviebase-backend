package ee.taltech.iti0302.webproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter @Setter
@Entity
public class Genre {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;
}

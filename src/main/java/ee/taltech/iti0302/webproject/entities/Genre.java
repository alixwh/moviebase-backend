package ee.taltech.iti0302.webproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter @Setter
@Entity
public class Genre {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;
}

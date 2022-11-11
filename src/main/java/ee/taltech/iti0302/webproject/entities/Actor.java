package ee.taltech.iti0302.webproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Actor {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}

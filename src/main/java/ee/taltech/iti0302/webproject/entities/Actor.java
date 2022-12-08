package ee.taltech.iti0302.webproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Actor {
    @Id
    private int id;
    private String name;
    private String profilePath;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}

package ee.taltech.iti0302.webproject.entities;

import ee.taltech.iti0302.webproject.api.externallistdto.CrewMemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Director extends CrewMemberDto {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies;
}

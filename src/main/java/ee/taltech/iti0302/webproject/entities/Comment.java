package ee.taltech.iti0302.webproject.entities;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;

    @ManyToOne
    @JoinColumn(name= "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name= "account_id")
    private Account account;
}
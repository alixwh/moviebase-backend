package ee.taltech.iti0302.webproject.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
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
package ee.taltech.iti0302.webproject.api;

import ee.taltech.iti0302.webproject.api.externallistdto.CreditsList;
import ee.taltech.iti0302.webproject.api.service.ApiService;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.entities.Movie;
import ee.taltech.iti0302.webproject.service.ActorService;
import ee.taltech.iti0302.webproject.service.DirectorService;
import ee.taltech.iti0302.webproject.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@RequestMapping("api")
@RestController
public class ApiController {
    private static final String API_KEY = "ee997f75fb7f7e80dc5adc5aabac24ff";
    private final ApiService apiService;
    private final ActorService actorService;
    private final DirectorService directorService;

    @PostMapping("save/genres")
    public void saveGenres() {
        apiService.saveGenres();
    }

    @PostMapping("save/movies/{limit}")
    public void saveMovies(@PathVariable("limit") Integer limit) {
        apiService.saveMovies(limit);
    }

    public void saveCredits(Integer movieId, Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/movie/%1$s/credits?api_key=%2$s&language=en-US", movieId, API_KEY);
        CreditsList response = restTemplate.getForObject(resourceUrl, CreditsList.class);
        int i = 0;
        Set<Actor> actors = new HashSet<>();
        for (Actor actor: response.getCast()) {
            if(i > 20) {
                break;
            }
            actorService.save(actor);
            actors.add(actor);
            i++;
        }
        movie.setActors(actors);
        Set<Director> directors = new HashSet<>();
        for (Director crewMember: response.getCrew()) {
            if (crewMember.getJob().equals("Director")) {
                directorService.save(crewMember);
                directors.add(crewMember);
            }
        }
        movie.setDirectors(directors);
    }
}

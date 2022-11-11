package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.api.externaldto.GenreExternalDto;
import ee.taltech.iti0302.webproject.api.externallistdto.GenreListExternalDto;
import ee.taltech.iti0302.webproject.api.mappper.GenreExternalMapper;
import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class GenreExternalService {
    private static final String API_KEY = "ee997f75fb7f7e80dc5adc5aabac24ff";
    private final GenreRepository genreRepository;
    private final GenreExternalMapper genreExternalMapper;

    public Genre findById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    public void saveGenres() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s&language=en-US", API_KEY);
        GenreListExternalDto response = restTemplate.getForObject(resourceUrl, GenreListExternalDto.class);
        for (GenreExternalDto genreExternalDto: Objects.requireNonNull(response).getGenres()) {
            Genre genre = genreExternalMapper.genreExternalDtoToGenre(genreExternalDto);
            save(genre);
        }
    }

    public Set<Genre> getGenresByIds(Set<Integer> genreIds) {
        Set<Genre> genres = new HashSet<>();
        for(Integer genreId: genreIds) {
            genres.add(findById(genreId));
        }
        return genres;
    }
}

package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.entities.Genre;
import ee.taltech.iti0302.webproject.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GenreExternalService {
    private final GenreRepository genreRepository;

    public Genre findById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    public void save(Genre genre) {
        genreRepository.save(genre);
    }
}

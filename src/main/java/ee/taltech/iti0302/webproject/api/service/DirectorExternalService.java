package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DirectorExternalService {
    private final DirectorRepository directorRepository;

    public void save(Director director) {
        directorRepository.save(director);
    }
}

package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
}

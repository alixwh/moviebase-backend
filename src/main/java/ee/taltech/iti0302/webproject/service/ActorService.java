package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActorService {
    private final ActorRepository actorRepository;
}
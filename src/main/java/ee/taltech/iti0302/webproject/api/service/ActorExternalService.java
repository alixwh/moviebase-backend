package ee.taltech.iti0302.webproject.api.service;

import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ActorExternalService {
    private final ActorRepository actorRepository;

    public void save(Actor actor) {
        actorRepository.save(actor);
    }
}
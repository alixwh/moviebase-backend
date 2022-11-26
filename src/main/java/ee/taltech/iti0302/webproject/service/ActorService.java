package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.ActorDto;
import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.entities.Actor;
import ee.taltech.iti0302.webproject.mapper.ActorMapper;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;
    private final MovieMapper movieMapper;

    public ActorDto findById(int id) {
        return actorMapper.toDto(actorRepository.findById(id).orElse(null));
    }

    public List<ActorDto> findAll() {
        return actorMapper.toDtoList(actorRepository.findAll());
    }

    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    public List<MovieDto> findMoviesByActorId(int id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor != null) {
            return movieMapper.toDtoList(actor.getMovies());
        }
        return new ArrayList<>();
    }
}
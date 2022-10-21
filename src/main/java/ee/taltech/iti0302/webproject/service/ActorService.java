package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.ActorDto;
import ee.taltech.iti0302.webproject.mapper.ActorMapper;
import ee.taltech.iti0302.webproject.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public List<ActorDto> findAll() {
        return actorMapper.toDtoList(actorRepository.findAll());
    }
}
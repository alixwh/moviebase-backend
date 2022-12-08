package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.DirectorDto;
import ee.taltech.iti0302.webproject.entities.Director;
import ee.taltech.iti0302.webproject.mapper.DirectorMapper;
import ee.taltech.iti0302.webproject.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public List<DirectorDto> findAll() {
        return directorMapper.toDtoList(directorRepository.findAll());
    }
}

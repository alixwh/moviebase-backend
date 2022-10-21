package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.GenreDto;
import ee.taltech.iti0302.webproject.mapper.GenreMapper;
import ee.taltech.iti0302.webproject.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreDto findById(int id) {
        return genreMapper.toDto(genreRepository.findById(id).orElse(null));
    }

    public List<GenreDto> findAll() {
        return genreMapper.toDtoList(genreRepository.findAll());
    }

}

package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.MovieDto;
import ee.taltech.iti0302.webproject.mapper.MovieMapper;
import ee.taltech.iti0302.webproject.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDto> findAll() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public MovieDto findById(int id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }

    public List<MovieDto> findByYear(int year) {
        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate startDate = instance.with(firstDayOfYear());
        LocalDate endDate = instance.with(lastDayOfYear());
        return movieMapper.toDtoList(movieRepository.findByReleaseDateBetween(startDate, endDate));
    }
}

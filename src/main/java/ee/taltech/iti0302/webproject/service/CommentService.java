package ee.taltech.iti0302.webproject.service;

import ee.taltech.iti0302.webproject.dto.CommentDto;
import ee.taltech.iti0302.webproject.mapper.CommentMapper;
import ee.taltech.iti0302.webproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentDto findById(int id) {
        return commentMapper.toDto(commentRepository.findById(id).orElse(null));
    }

    public List<CommentDto> findAll() {
        return commentMapper.toDtoList(commentRepository.findAll());
    }
}

package de.uni_stuttgart.it_rex.quiz.service.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;
import de.uni_stuttgart.it_rex.quiz.repository.written.QuizRepository;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuizDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuizMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Quiz}.
 */
@Service
public class QuizService {

    private final Logger log = LoggerFactory.getLogger(QuizService.class);

    private final QuizRepository quizRepository;

    private final QuizMapper quizMapper;

    public QuizService(QuizRepository newQuizRepository, QuizMapper newQuizMapper) {
        this.quizRepository = newQuizRepository;
        this.quizMapper = newQuizMapper;
    }

    /**
     * Save a Quiz.
     *
     * @param QuizDTO the entity to save.
     * @return the persisted entity.
     */
    public QuizDTO save(final QuizDTO quizDTO) {
        log.debug("Request to save Quiz : {}", quizDTO);
        Quiz quiz = quizMapper.toEntity(quizDTO);
        quiz = quizRepository.save(quiz);
        return quizMapper.toDto(quiz);
    }

    /**
     * Get all the QuizS.
     *
     * @return the list of entities.
     */
    public List<QuizDTO> findAll() {
        log.debug("Request to get all QuizS");
        return quizRepository.findAll().stream()
            .map(quizMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one Quiz by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<QuizDTO> findOne(final UUID id) {
        log.debug("Request to get Quiz : {}", id);
        return quizRepository.findById(id).map(quizMapper::toDto);
    }

    /**
     * Delete the Quiz by id.
     *
     * @param id the id of the entity.
     */
    public void delete(final UUID id) {
        log.debug("Request to delete Quiz : {}", id);
        quizRepository.deleteById(id);
    }
}

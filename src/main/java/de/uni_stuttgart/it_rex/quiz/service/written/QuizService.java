package de.uni_stuttgart.it_rex.quiz.service.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Quiz;
import de.uni_stuttgart.it_rex.quiz.repository.written.QuizRepository;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuizDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuizMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Quiz}.
 */
@Service
@Transactional
public class QuizService {

    private final Logger log = LoggerFactory.getLogger(QuizService.class);

    private final QuizRepository quizRepository;

    private final QuizMapper quizMapper;

    private final QuestionService questionService;

    public QuizService(QuizRepository newQuizRepository, QuizMapper newQuizMapper, QuestionService newQuestionService) {
        this.quizRepository = newQuizRepository;
        this.quizMapper = newQuizMapper;
        this.questionService = newQuestionService;
    }

    /**
     * Save a Quiz.
     *
     * @param QuizDTO the entity to save.
     * @return the persisted entity.
     */
    public QuizDTO save(final QuizDTO quizDTO) {
        log.debug("Request to save Quiz : {}", quizDTO);

        // create questions
        quizDTO.setQuestions(questionService.save(quizDTO.getQuestions()));

        // create quiz
        Quiz quiz = quizMapper.toEntity(quizDTO);
        quiz = quizRepository.save(quiz);

        // add questions
        // todo: inefficient: avoid saving questions twice!
        quiz.addQuestions(quiz.getQuestions());
        questionService.saveEntities(quiz.getQuestions().stream().collect(Collectors.toCollection(LinkedList::new)));

        return quizMapper.toDto(quiz);
    }

    /**
     * Get all the Quizzes.
     *
     * @return the list of entities.
     */
    public List<QuizDTO> findAll() {
        log.debug("Request to get Course Quizzes");
        return quizRepository.findAll().stream()
            .map(quizMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Quizzes.
     *
     * @return the list of entities.
     */
    public List<QuizDTO> findByCourseId(final UUID courseId) {
        log.debug("Request to get Course Quizzes");
        return quizRepository.findByCourseId(courseId).stream()
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

    /**
     * Delete the all Quizzes by id.
     *
     * @param ids the ids of the Quizzes.
     */
    public void deleteAll(final List<UUID> ids) {
        log.debug("Request to delete all Quizzes : {}", ids);
        quizRepository.deleteByIdIn(ids);
    }

    /**
     * Delete the Quiz by id.
     *
     * @param id the id of the entity.
     */
    public void delete(final UUID id, boolean withQuestions) {
        log.debug("Request to delete Quiz with Questions: {}", id);
        if (withQuestions) {
            Optional<Quiz> quiz = quizRepository.findById(id);
            if (quiz.isPresent()) {
                List<UUID> questionIds = quiz.get().getQuestions().stream().map(Question::getId).collect(Collectors.toList());
                questionService.deleteByIdIn(questionIds);
            }
        }
        delete(id);
    }
}

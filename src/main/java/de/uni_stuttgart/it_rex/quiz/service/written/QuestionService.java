package de.uni_stuttgart.it_rex.quiz.service.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;
import de.uni_stuttgart.it_rex.quiz.repository.written.QuestionRepository;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Question}.
 */
@Service
public class QuestionService {

    private final Logger log = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository newQuestionRepository, QuestionMapper newQuestionMapper) {
        this.questionRepository = newQuestionRepository;
        this.questionMapper = newQuestionMapper;
    }

    /**
     * Save a Question.
     *
     * @param QuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionDTO save(final QuestionDTO questionDTO) {
        log.debug("Request to save QuestionDTO : {}", questionDTO);
        Question question = questionMapper.toEntity(questionDTO);

        // if it exists, ignore new QuizIds
        if (!question.isNew()) {
            Optional<Question> questionDb = questionRepository.findById(question.getId());
            if (questionDb.isPresent()) {
                question.setQuizIds(questionDb.get().getQuizIds());
            }
        }

        question = questionRepository.save(question);
        return questionMapper.toDto(question);
    }

    /**
     * Save a Question.
     *
     * @param QuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public List<QuestionDTO> save(final List<QuestionDTO> questionDTOs) {
        log.debug("Request to save QuestionsDTO : {}", questionDTOs);
        List<Question> questions = questionMapper.toEntity(questionDTOs);

        // if Question exists, ignore new QuizIds
        List<UUID> ids = questions.stream().filter(o -> !o.isNew()).map(Question::getId).collect(Collectors.toCollection(LinkedList::new));
        Map<UUID, Question> questionsDb = questionRepository.findByIdIn(ids).stream().collect(Collectors.toMap(Question::getId, o -> o));
        questions.forEach(o -> {
            Optional<Question> q = Optional.ofNullable(questionsDb.get(o.getId()));
            if (q.isPresent()) {
                o.setQuizIds(q.get().getQuizIds());
            }
        });

        questions = questionRepository.saveAll(questions);
        return questionMapper.toDto(questions);
    }

    /**
     * Save a Question.
     *
     * @param Question the entity to save.
     * @return the persisted entity.
     */
    public Question saveEntity(final Question question) {
        log.debug("Request to save Question : {}", question);
        return questionRepository.save(question);
    }

    /**
     * Save a Question.
     *
     * @param Question the entity to save.
     * @return the persisted entity.
     */
    public List<Question> saveEntities(final List<Question> questions) {
        log.debug("Request to save Questions : {}", questions);
        return questionRepository.saveAll(questions);
    }

    /**
     * Get all the Questions.
     *
     * @return the list of entities.
     */
    public List<QuestionDTO> findAll() {
        log.debug("Request to get all Questions");
        return questionRepository.findAll().stream()
            .map(questionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Questions.
     *
     * @return the list of entities.
     */
    public List<QuestionDTO> findAll(final UUID courseId) {
        log.debug("Request to get all Questions");
        return questionRepository.findByCourseId(courseId).stream()
            .map(questionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Questions.
     *
     * @return the list of entities.
     */
    public List<QuestionDTO> findByIdIn(final List<UUID> ids) {
        log.debug("Request to get List of Questions : {}", ids);
        return questionRepository.findByIdIn(ids).stream().map(questionMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one Question by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<QuestionDTO> findOne(final UUID id) {
        log.debug("Request to get Question : {}", id);
        return questionRepository.findById(id).map(questionMapper::toDto);
    }

    /**
     * Delete the Question by id.
     *
     * @param id the id of the entity.
     */
    public void delete(final UUID id) {
        log.debug("Request to delete Question : {}", id);
        questionRepository.deleteById(id);
    }

    /**
     * Delete the all Questions by id.
     *
     * @param ids the ids of the Questions.
     */
    public void deleteByIdIn(final List<UUID> ids) {
        log.debug("Request to delete all Questions : {}", ids);
        questionRepository.deleteByIdIn(ids);
    }
}

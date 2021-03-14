package de.uni_stuttgart.it_rex.quiz.service.written;

import de.uni_stuttgart.it_rex.quiz.domain.written_entities.Question;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionMultipleChoice;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionNumeric;
import de.uni_stuttgart.it_rex.quiz.domain.written_entities.QuestionSingleChoice;
import de.uni_stuttgart.it_rex.quiz.repository.written.QuestionRepository;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionMultipleChoiceDTO;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionNumericDTO;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionSingleChoiceDTO;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuestionMapper;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuestionMultipleChoiceMapper;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuestionNumericMapper;
import de.uni_stuttgart.it_rex.quiz.service.mapper.written.QuestionSingleChoiceMapper;

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
    private final QuestionSingleChoiceMapper questionSingleChoiceMapper;
    private final QuestionMultipleChoiceMapper questionMultipleChoiceMapper;
    private final QuestionNumericMapper questionNumericMapper;

    public QuestionService(QuestionRepository newQuestionRepository,
            QuestionMapper newQuestionMapper,
            QuestionSingleChoiceMapper newQuestionSingleChoiceMapper,
            QuestionMultipleChoiceMapper newQuestionMultipleChoiceMapper,
            QuestionNumericMapper newQuestionNumericMapper) {
        this.questionRepository = newQuestionRepository;
        this.questionMapper = newQuestionMapper;
        this.questionSingleChoiceMapper = newQuestionSingleChoiceMapper;
        this.questionMultipleChoiceMapper = newQuestionMultipleChoiceMapper;
        this.questionNumericMapper = newQuestionNumericMapper;
    }

    Question mapDto(QuestionDTO questionDTO) {
        if (questionDTO instanceof QuestionSingleChoiceDTO) {
            return questionSingleChoiceMapper.toEntity((QuestionSingleChoiceDTO)questionDTO);
        }
        else if (questionDTO instanceof QuestionMultipleChoiceDTO) {
            return questionMultipleChoiceMapper.toEntity((QuestionMultipleChoiceDTO)questionDTO);
        }
        else if (questionDTO instanceof QuestionNumericDTO) {
            return questionNumericMapper.toEntity((QuestionNumericDTO)questionDTO);
        }
        return questionMapper.toEntity(questionDTO);
    }

    List<Question> mapDtos(List<QuestionDTO> questionDTOs) {
        return questionDTOs.stream().map(this::mapDto).collect(Collectors.toCollection(LinkedList::new));
    }

    QuestionDTO mapEntity(Question question) {
        if (question instanceof QuestionSingleChoice) {
            return questionSingleChoiceMapper.toDto((QuestionSingleChoice)question);
        }
        else if (question instanceof QuestionMultipleChoice) {
            return questionMultipleChoiceMapper.toDto((QuestionMultipleChoice)question);
        }
        else if (question instanceof QuestionNumeric) {
            return questionNumericMapper.toDto((QuestionNumeric)question);
        }
        return questionMapper.toDto(question);
    }

    List<QuestionDTO> mapEntities(List<Question> questions) {
        return questions.stream().map(this::mapEntity).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Save a Question.
     *
     * @param QuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public QuestionDTO save(final QuestionDTO questionDTO) {
        log.debug("Request to save QuestionDTO : {}", questionDTO);

        Question question = mapDto(questionDTO);

        // if it exists, ignore new QuizIds
        if (!question.isNew()) {
            Optional<Question> questionDb = questionRepository.findById(question.getId());
            if (questionDb.isPresent()) {
                question.setQuizIds(questionDb.get().getQuizIds());
            }
        }

        question = questionRepository.save(question);
        return mapEntity(question);
    }

    /**
     * Save a Question.
     *
     * @param QuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public List<QuestionDTO> save(final List<QuestionDTO> questionDTOs) {
        log.debug("Request to save QuestionsDTO : {}", questionDTOs);
        List<Question> questions = mapDtos(questionDTOs);

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
        return mapEntities(questions);
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

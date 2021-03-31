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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

        // if new create quiz
        Quiz quiz = quizMapper.toEntityWithoutQuestions(quizDTO);
        Optional<Quiz> quizDbOpt = Optional.empty();
        if (quiz.isNew()) {
            quiz = quizRepository.save(quiz);
        }
        else {
            quizDbOpt = quizRepository.findById(quiz.getId());
        }

        // remove quiz ids from previously contained questions
        List<Question> questions = quizMapper.toEntity(quizDTO).getQuestions();
        if (quizDbOpt.isPresent()) {
            Quiz quizDb = quizDbOpt.get();
            List<Question> questionssDb = quizDb.getQuestions();
            Set<Question> questionsNew = questions.stream().collect(Collectors.toSet());
            Set<Question> questionsOld = questionssDb.stream().collect(Collectors.toSet());
            questionsOld.removeAll(questionsNew);
            questionsOld.forEach(o -> o.removeQuizId(quizDb.getId()));
            questionService.saveEntities(questionsOld.stream().collect(Collectors.toCollection(LinkedList::new)));
        }

        // get quiz ids from questions
        List<Question> questionsDb = questionService.findEntitiesByIdIn(questions.stream().map(Question::getId).collect(Collectors.toCollection(LinkedList::new)));
        Map<UUID, Question> questionMap = questionsDb.stream().collect(Collectors.toMap(Question::getId, question -> question));
        questions.forEach(question -> {
            Optional<Question> questionOpt = Optional.ofNullable(questionMap.get(question.getId()));
            if (questionOpt.isPresent())
            {
                question.getQuizIds().addAll(questionOpt.get().getQuizIds());
            }
        });

        // add questions
        quiz.addQuestions(questions);

        // save questions
        questionService.saveEntities(questions);

        // save quiz
        quiz = quizRepository.save(quiz);

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
     * Get Quizzes by list of Quiz ids.
     *
     * @param ids the Quiz ids
     * @return the list of entities.
     */
    public List<QuizDTO> findByIdIn(final List<UUID> ids) {
        log.debug("Request to get List of Quizzes : {}", ids);
        return quizRepository.findByIdIn(ids).stream().map(quizMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all Quizzes of a Course.
     *
     * @param courseId the Course Id
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
     * Also removes the quiz id from the contained questions.
     *
     * @param id the id of the entity.
     */
    public void delete(final UUID id) {
        log.debug("Request to delete Quiz : {}", id);

        Optional<Quiz> quizDbOpt = quizRepository.findById(id);
        // remove quiz id in contained questions
        if (quizDbOpt.isPresent()) {
            Quiz quizDb = quizDbOpt.get();
            List<Question> questionsDb = quizDb.getQuestions();
            questionsDb.forEach(o -> o.removeQuizId(quizDb.getId()));
            questionService.saveEntities(questionsDb);
        }

        quizRepository.deleteById(id);
    }

    /**
     * Delete Quizzes by id.
     * Also removes the quiz ids from the contained questions.
     *
     * @param ids the ids of the Quizzes.
     */
    public void deleteByIdIn(final List<UUID> ids) {
        log.debug("Request to delete all Quizzes : {}", ids);

        // remove quiz ids in contained questions
        List<Quiz> quizzesDb = quizRepository.findByIdIn(ids);
        Set<Question> questionsToSave = new HashSet<>();
        quizzesDb.forEach(quiz -> {
            List<Question> questions = quiz.getQuestions();
            questions.forEach(o -> o.removeQuizId(quiz.getId()));
            questionsToSave.addAll(questions);
        });
        questionService.saveEntities(questionsToSave.stream().collect(Collectors.toCollection(LinkedList::new)));

        quizRepository.deleteByIdIn(ids);
    }

    /**
     * Delete the Quiz by id.
     *
     * @param id the id of the entity.
     * @param withQuestions if {@code true} deletes also the contained questions, if they are not contained in any other quiz
     */
    public void delete(final UUID id, boolean withQuestions) {
        log.debug("Request to delete Quiz with Questions: {}", id);
        Optional<Quiz> quizDbOpt = quizRepository.findById(id);
        if (quizDbOpt.isPresent()) {
            Quiz quizDb = quizDbOpt.get();
            List<Question> questionsDb = quizDb.getQuestions();
            questionsDb.forEach(o -> o.removeQuizId(quizDb.getId()));
            if (withQuestions) {
                List<Question> questionsToDelete = questionsDb.stream().filter(o -> o.getQuizIds().isEmpty()).collect(Collectors.toCollection(LinkedList::new));
                questionsDb.removeAll(questionsToDelete);
                questionService.deleteEntities(questionsToDelete);
            }
            questionService.saveEntities(questionsDb);
        }
        quizRepository.deleteById(id);
    }
}

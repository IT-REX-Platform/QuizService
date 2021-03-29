package de.uni_stuttgart.it_rex.quiz.web.rest.written;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

import de.uni_stuttgart.it_rex.quiz.service.written.QuestionService;
import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuestionDTO;
import de.uni_stuttgart.it_rex.quiz.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link User}.
 */
@RestController
@RequestMapping("/api")
public class QuestionResource {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(QuestionResource.class);

    /**
     * Entity name.
     */
    private static final String ENTITY_NAME = "Question";

    /**
     * Application name.
     */
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionService questionService;

    /**
     * Constructor.
     *
     * @param newChapterService the chapter service.
     */
    @Autowired
    public QuestionResource(
        final QuestionService newQuestionService) {
        this.questionService = newQuestionService;
    }

    /**
     * {@code POST  /questions} : Create a new Question.
     *
     * @param QuestionDTO the questionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new QuestionDTO, or with status {@code 400 (Bad Request)} if the entityA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to save Question : {}", questionDTO);
        if (questionDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionDTO cannot already have an ID", ENTITY_NAME, "id exists");
        }
        QuestionDTO result = questionService.save(questionDTO);
        return ResponseEntity.created(new URI("/api/questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /questions} : Updates an existing Question.
     *
     * @param QuestionDTO the QuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated QuestionDTO,
     * or with status {@code 400 (Bad Request)} if the QuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the QuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/questions")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO questionDTO) throws URISyntaxException {
        log.debug("REST request to update Question : {}", questionDTO);
        if (questionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuestionDTO result = questionService.save(questionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /questions} : get all the questions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questions in body.
     */
    @GetMapping("/questions")
    public List<QuestionDTO> getAllQuestions() {
        log.debug("REST request to get all Questions");
        return questionService.findAll();
    }

    /**
     * {@code GET  /questions} : get all the questions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questions in body.
     */
    @GetMapping(value="/questions", params="course_id")
    public List<QuestionDTO> getCourseQuestions(@RequestParam("course_id") final UUID courseId) {
        log.debug("REST request to get Course Questions");
        return questionService.findAll(courseId);
    }

    /**
     * {@code GET  /questions/ids} : get questions by ids.
     *
     * @param questionIds the Questions Ids.
     * @return the map of questions in the body.
     */
    @GetMapping(value="/questions/ids")
    public Map<UUID, QuestionDTO> findAllByIds(@RequestBody final List<UUID> questionIds) {
        log.info("REST request to get all questions by ids: {}", questionIds);
        final List<QuestionDTO> questions = questionService.findByIdIn(questionIds);
        return questions.stream().collect(Collectors.toMap(QuestionDTO::getId, question -> question));
    }

    /**
     * {@code GET  /questions/:id} : get the "id" question.
     *
     * @param id the id of the QuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the QuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable final UUID id) {
        log.debug("REST request to get Question : {}", id);
        Optional<QuestionDTO> questionDTO = questionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionDTO);
    }

    /**
     * {@code DELETE  /questions/:id} : delete the "id" question.
     *
     * @param id the id of the QuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable final UUID id) {
        log.debug("REST request to delete Questions : {}", id);
        questionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}


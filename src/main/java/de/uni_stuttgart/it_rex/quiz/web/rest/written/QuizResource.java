package de.uni_stuttgart.it_rex.quiz.web.rest.written;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.uni_stuttgart.it_rex.quiz.service.written.QuizService;
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

import de.uni_stuttgart.it_rex.quiz.service.dto.written_dtos.QuizDTO;
import de.uni_stuttgart.it_rex.quiz.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link User}.
 */
@RestController
@RequestMapping("/api")
public class QuizResource {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(QuizResource.class);

    /**
     * Entity name.
     */
    private static final String ENTITY_NAME = "Quiz";

    /**
     * Application name.
     */
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuizService quizService;

    /**
     * Constructor.
     *
     * @param newChapterService the chapter service.
     */
    @Autowired
    public QuizResource(
        final QuizService newQuizService) {
        this.quizService = newQuizService;
    }

    /**
     * {@code POST  /quizzes} : Create a new Quiz.
     *
     * @param QuizDTO the quizDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new QuizDTO, or with status {@code 400 (Bad Request)} if the entityA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quizzes")
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO quizDTO) throws URISyntaxException {
        log.debug("REST request to save Quiz : {}", quizDTO);
        if (quizDTO.getId() != null) {
            throw new BadRequestAlertException("A new quizDTO cannot already have an ID", ENTITY_NAME, "id exists");
        }
        QuizDTO result = quizService.save(quizDTO);
        return ResponseEntity.created(new URI("/api/quizzes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quizzes} : Updates an existing Quiz.
     *
     * @param QuizDTO the QuizDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated QuizDTO,
     * or with status {@code 400 (Bad Request)} if the QuizDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the QuizDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quizzes")
    public ResponseEntity<QuizDTO> updateQuiz(@RequestBody QuizDTO quizDTO) throws URISyntaxException {
        log.debug("REST request to update Quiz : {}", quizDTO);
        if (quizDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuizDTO result = quizService.save(quizDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, quizDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quizzes} : get all the quizzes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quizzes in body.
     */
    @GetMapping("/quizzes")
    public List<QuizDTO> getAllQuizzes() {
        log.debug("REST request to get all Quizzes");
        return quizService.findAll();
    }

    /**
     * {@code GET  /quizzes} : get all the quizzes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quizzes in body.
     */
    @GetMapping(value="/quizzes", params="course_id")
    public List<QuizDTO> getCourseQuizzes(@RequestParam("course_id") final UUID courseId) {
        log.debug("REST request to get all Quizzes");
        return quizService.findByCourseId(courseId);
    }

    /**
     * {@code GET  /quizzes/:id} : get the "id" quiz.
     *
     * @param id the id of the QuizDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the QuizDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quizzes/{id}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable final UUID id) {
        log.debug("REST request to get Quiz : {}", id);
        Optional<QuizDTO> quizDTO = quizService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quizDTO);
    }

    /**
     * {@code DELETE  /quizzes/:id} : delete the "id" quiz.
     *
     * @param id the id of the QuizDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable final UUID id,
            @RequestParam(value = "with_questions", required = false, defaultValue = "false") final boolean withQuestions) {
        log.debug("REST request to delete quiz : {}", id);
        quizService.delete(id, withQuestions);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

}

